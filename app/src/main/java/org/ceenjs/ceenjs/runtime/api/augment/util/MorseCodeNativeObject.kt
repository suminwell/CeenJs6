package org.ceenjs.ceenjs.runtime.api.augment.util

import org.ceenjs.ceenjs.annotation.RhinoFunctionBody
import org.ceenjs.ceenjs.annotation.RhinoFunctionObjectBody
import org.ceenjs.ceenjs.annotation.RhinoStandardFunctionInterface
import org.ceenjs.ceenjs.rhino.ArgumentGuards
import org.ceenjs.ceenjs.rhino.ArgumentGuards.Companion.component1
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.isJsNullish
import org.ceenjs.ceenjs.rhino.extension.IterableExtensions.toNativeArray
import org.ceenjs.ceenjs.runtime.api.StringReadable
import org.ceenjs.ceenjs.util.RhinoUtils
import org.ceenjs.ceenjs.util.RhinoUtils.NOT_CONSTRUCTABLE
import org.ceenjs.ceenjs.util.RhinoUtils.newBaseFunction
import org.mozilla.javascript.Context
import org.mozilla.javascript.Function
import org.mozilla.javascript.NativeObject
import org.mozilla.javascript.Scriptable

@Suppress("unused", "UNUSED_PARAMETER")
class MorseCodeNativeObject(val parser: MorseCodeParser) : NativeObject(), StringReadable {

    private val mFunctionNames = arrayOf(
        "toString",
        ::getPattern.name,
        ::getCode.name,
        ::vibrate.name,
    )

    init {
        RhinoUtils.initNativeObjectPrototype(this)
        defineFunctionProperties(mFunctionNames, javaClass, PERMANENT)
        defineProperty(StringReadable.KEY, newBaseFunction(StringReadable.KEY, { toStringReadable() }, NOT_CONSTRUCTABLE), READONLY or DONTENUM or PERMANENT)
        defineProperty("pattern", { parser.pattern.toNativeArray() }, null, PERMANENT)
        defineProperty("code", { parser.code }, null, PERMANENT)
    }

    @RhinoFunctionObjectBody
    override fun toStringReadable() = toStringRhino(this)

    companion object : ArgumentGuards() {

        @JvmStatic
        @RhinoStandardFunctionInterface
        fun getPattern(cx: Context, thisObj: Scriptable, args: Array<Any?>, funObj: Function): Any = ensureArgumentsIsEmpty(args) {
            (thisObj as MorseCodeNativeObject).parser.pattern.toNativeArray()
        }

        @JvmStatic
        @RhinoStandardFunctionInterface
        fun getCode(cx: Context, thisObj: Scriptable, args: Array<Any?>, funObj: Function): Any = ensureArgumentsIsEmpty(args) {
            (thisObj as MorseCodeNativeObject).parser.code
        }

        @JvmStatic
        @RhinoStandardFunctionInterface
        fun toString(cx: Context, thisObj: Scriptable, args: Array<Any?>, funObj: Function): String = ensureArgumentsIsEmpty(args) {
            toStringRhino(thisObj as MorseCodeNativeObject)
        }

        @JvmStatic
        @RhinoFunctionBody
        fun toStringRhino(thisObj: MorseCodeNativeObject): String {
            val parser = thisObj.parser
            val code = parser.code
            val pattern = parser.pattern
            return listOf(
                "${MorseCode::class.java.simpleName} {",
                "  code: '$code',",
                "  pattern: ${Util.formatRhino(pattern)},",
                "}",
            ).joinToString("\n")
        }

        @JvmStatic
        @RhinoStandardFunctionInterface
        fun vibrate(cx: Context, thisObj: Scriptable, args: Array<Any?>, funObj: Function): Any = ensureArgumentsAtMost(args, 1) {
            val (delay) = it
            (thisObj as MorseCodeNativeObject).parser.vibrate(if (!delay.isJsNullish()) Context.toNumber(delay) else 0.0)
        }
    }
}
