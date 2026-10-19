package org.ceenjs.ceenjs.runtime.api.augment.plugins

import org.ceenjs.ceenjs.annotation.RhinoRuntimeFunctionInterface
import org.ceenjs.ceenjs.rhino.ArgumentGuards
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.api.augment.Invokable
import org.ceenjs.ceenjs.runtime.exception.WrappedIllegalArgumentException
import org.ceenjs.ceenjs.util.RhinoUtils.callFunction
import org.ceenjs.ceenjs.util.RhinoUtils.coerceString
import org.ceenjs.ceenjs.util.RhinoUtils.js_require
import org.mozilla.javascript.BaseFunction
import kotlin.text.RegexOption.IGNORE_CASE

@Suppress("unused", "UNUSED_PARAMETER")
class Plugins(private val scriptRuntime: ScriptRuntime) : Augmentable(scriptRuntime), Invokable {

    override val selfAssignmentFunctions = listOf(
        ::load.name,
    )

    override fun invoke(vararg args: Any?): Any? = ensureArgumentsOnlyOne(args) { o ->
        load(scriptRuntime, arrayOf(o))
    }

    companion object : ArgumentGuards() {

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun load(scriptRuntime: ScriptRuntime, args: Array<out Any?>): Any? = ensureArgumentsOnlyOne(args) { o ->
            val name = coerceString(o)
            val asPackageName = name.contains('.') && !name.matches(Regex(".+\\.js", IGNORE_CASE))
            when {
                asPackageName -> {
                    val plugin = scriptRuntime.plugins.load(name)
                    val moduleExportedFunc = js_require(scriptRuntime, plugin.mainScriptPath) as BaseFunction
                    val global = scriptRuntime.topLevelScope
                    val scope = moduleExportedFunc.parentScope ?: global
                    callFunction(scriptRuntime, moduleExportedFunc, scope, global, arrayOf(plugin.unwrap()))
                }
                scriptRuntime.files.isDir("./plugins") -> {
                    /* As project-level plugins name. */
                    js_require(scriptRuntime, "./plugins/$name")
                }
                else -> listOf(
                    "A directory named \"plugins\" containing module \"$name\"",
                    "must be found in the root directory of current project",
                ).joinToString(" ").let { s -> throw WrappedIllegalArgumentException(s) }
            }
        }

    }

}
