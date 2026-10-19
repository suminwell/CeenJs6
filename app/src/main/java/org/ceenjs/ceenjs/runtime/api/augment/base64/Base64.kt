package org.ceenjs.ceenjs.runtime.api.augment.base64

import org.ceenjs.ceenjs.annotation.RhinoFunctionBody
import org.ceenjs.ceenjs.annotation.RhinoSingletonFunctionInterface
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.isJsNullish
import org.ceenjs.ceenjs.rhino.extension.NativeArrayExtensions.jsBytesToByteArray
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.exception.WrappedIllegalArgumentException
import org.ceenjs.ceenjs.util.ArrayUtils
import org.mozilla.javascript.Context
import org.mozilla.javascript.NativeArray
import java.nio.charset.Charset
import android.util.Base64 as AndroidBase64

object Base64 : Augmentable() {

    override val selfAssignmentFunctions = listOf(
        ::encode.name,
        ::decode.name,
    )

    @JvmStatic
    @RhinoSingletonFunctionInterface
    fun encode(args: Array<out Any?>): String = ensureArgumentsLengthInRange(args, 1..2) {
        val (o, encoding) = it
        encodeRhino(o, encoding)
    }

    @JvmStatic
    @RhinoFunctionBody
    fun encodeRhino(o: Any?, encoding: Any?): String {
        return AndroidBase64.encodeToString(toBytes(o, parseEncoding(encoding)), AndroidBase64.NO_WRAP)
    }

    @JvmStatic
    @RhinoSingletonFunctionInterface
    fun decode(args: Array<out Any?>): String = ensureArgumentsLengthInRange(args, 1..2) {
        val (o, encoding) = it
        decodeRhino(o, encoding)
    }

    @JvmStatic
    @RhinoFunctionBody
    fun decodeRhino(o: Any?, encoding: Any?): String {
        val niceEncoding = parseEncoding(encoding)
        val decoded = AndroidBase64.decode(toBytes(o, niceEncoding), AndroidBase64.NO_WRAP)
        return String(decoded, niceEncoding)
    }

    /**
     * Ignored (but not fuzzy) regex matching for non-word characters.
     */
    private fun parseEncoding(encoding: Any?): Charset {
        if (encoding.isJsNullish()) return Charset.defaultCharset()
        val niceEncoding = Context.toString(encoding)
        val charsetEncoding = niceEncoding.trim().lowercase().replace("\\W+".toRegex(), "")
        return Charset.availableCharsets().values.find {
            it.name().lowercase().replace("\\W+".toRegex(), "") == charsetEncoding
        } ?: Charset.defaultCharset()
    }

    private fun toBytes(o: Any?, encoding: Charset = Charset.defaultCharset()): ByteArray = when {
        o.isJsNullish() -> throw WrappedIllegalArgumentException("Cannot convert o ($o) to byte[]")
        o is String -> o.toByteArray(encoding)
        o is ByteArray -> o
        o is NativeArray -> o.jsBytesToByteArray()
        else -> toBytes(o.toString())
    }

}