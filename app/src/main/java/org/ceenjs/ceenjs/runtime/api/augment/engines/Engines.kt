package org.ceenjs.ceenjs.runtime.api.augment.engines

import org.ceenjs.ceenjs.annotation.RhinoRuntimeFunctionInterface
import org.ceenjs.ceenjs.engine.JavaScriptEngine
import org.ceenjs.ceenjs.engine.ScriptEngine
import org.ceenjs.ceenjs.execution.ExecutionConfig
import org.ceenjs.ceenjs.execution.ScriptExecution
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.isJsNullish
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.jsBrief
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.toRuntimePath
import org.ceenjs.ceenjs.rhino.ArgumentGuards
import org.ceenjs.ceenjs.rhino.extension.ScriptableExtensions.prop
import org.ceenjs.ceenjs.rhino.extension.ScriptableObjectExtensions.inquire
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.augment.AsEmitter
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.exception.WrappedIllegalArgumentException
import org.ceenjs.ceenjs.script.ScriptSource
import org.ceenjs.ceenjs.util.RhinoUtils.callFunction
import org.ceenjs.ceenjs.util.RhinoUtils.coerceIntNumber
import org.ceenjs.ceenjs.util.RhinoUtils.coerceLongNumber
import org.ceenjs.ceenjs.util.RhinoUtils.coerceString
import org.ceenjs.ceenjs.util.RhinoUtils.newNativeObject
import org.mozilla.javascript.BaseFunction
import org.mozilla.javascript.NativeArray
import org.mozilla.javascript.NativeObject
import org.mozilla.javascript.ScriptableObject

@Suppress("unused")
class Engines(private val scriptRuntime: ScriptRuntime) : Augmentable(scriptRuntime), AsEmitter {

    override val selfAssignmentFunctions = listOf(
        ::all.name,
        ::myEngine.name,
        ::getEngines.name,
        ::stopAll.name,
        ::stopAllAndToast.name,
        ::execScript.name,
        ::execScriptFile.name,
        ::execAutoFile.name,
    )

    init {
        setEngineExecArgv()
    }

    private fun setEngineExecArgv() {
        when (val tag = scriptRuntime.engines.myEngine().getTag(ExecutionConfig.tag)) {
            is ExecutionConfig -> {
                val execArgv = newNativeObject()
                val iterator = tag.arguments.entries.iterator()
                while (iterator.hasNext()) {
                    val entry = iterator.next()
                    put(execArgv, entry.key to entry.value)
                }
                scriptRuntime.engines.myEngine().setExecArgv(execArgv)
            }
        }
    }

    companion object : ArgumentGuards() {

        @JvmStatic
        fun emit(scriptRuntime: ScriptRuntime, eventName: String, vararg args: Any?) {
            val engines = scriptRuntime.topLevelScope.prop("engines") as? ScriptableObject ?: return
            val emitFunc = engines.prop("emit") as BaseFunction
            callFunction(scriptRuntime, emitFunc, arrayOf(eventName, *args))
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun all(scriptRuntime: ScriptRuntime, args: Array<out Any?>): NativeArray = ensureArgumentsIsEmpty(args) {
            scriptRuntime.engines.all()
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun myEngine(scriptRuntime: ScriptRuntime, args: Array<out Any?>): JavaScriptEngine = ensureArgumentsIsEmpty(args) {
            scriptRuntime.engines.myEngine()
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun getEngines(scriptRuntime: ScriptRuntime, args: Array<out Any?>): Set<ScriptEngine<out ScriptSource>> = ensureArgumentsIsEmpty(args) {
            scriptRuntime.engines.engines
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun stopAll(scriptRuntime: ScriptRuntime, args: Array<out Any?>): Int = ensureArgumentsIsEmpty(args) {
            scriptRuntime.engines.stopAll()
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun stopAllAndToast(scriptRuntime: ScriptRuntime, args: Array<out Any?>): Int = ensureArgumentsIsEmpty(args) {
            scriptRuntime.engines.stopAllAndToast()
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun execScript(scriptRuntime: ScriptRuntime, args: Array<out Any?>): ScriptExecution = ensureArgumentsLengthInRange(args, 2..3) {
            val (name, script, config) = it
            scriptRuntime.engines.execScript(coerceString(name), coerceString(script), fillConfig(scriptRuntime, config))
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun execScriptFile(scriptRuntime: ScriptRuntime, args: Array<out Any?>): ScriptExecution = ensureArgumentsLengthInRange(args, 1..2) {
            val (path, config) = it
            scriptRuntime.engines.execScriptFile(coerceString(path), fillConfig(scriptRuntime, config))
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun execAutoFile(scriptRuntime: ScriptRuntime, args: Array<out Any?>): ScriptExecution = ensureArgumentsLengthInRange(args, 1..2) {
            val (path, config) = it
            scriptRuntime.engines.execAutoFile(coerceString(path), fillConfig(scriptRuntime, config))
        }

        private fun fillConfig(scriptRuntime: ScriptRuntime, o: Any?): ExecutionConfig {
            val result = ExecutionConfig()
            when (val config = if (o.isJsNullish()) newNativeObject() else o) {
                is ExecutionConfig -> {
                    result.workingDirectory = config.workingDirectory.takeUnless { it.isBlank() } ?: scriptRuntime.files.cwd() ?: ""
                    result.delay = config.delay.takeUnless { it < 0 } ?: 0L
                    result.interval = config.interval.takeUnless { it < 0 } ?: 0L
                    result.loopTimes = config.loopTimes.takeUnless { it < 0 } ?: 1
                    config.arguments.entries.forEach { result.setArgument(it.key, it.value) }
                }
                is ScriptableObject -> {
                    result.workingDirectory = config.inquire(listOf("path", "workingDirectory"), { o, _ -> o.toRuntimePath(scriptRuntime) }, scriptRuntime.files.cwd() ?: "")
                    result.delay = config.inquire("delay", ::coerceLongNumber, 0L)
                    result.interval = config.inquire("interval", ::coerceLongNumber, 0L)
                    result.loopTimes = config.inquire("loopTimes", ::coerceIntNumber, 1)
                    config.inquire("arguments") {
                        require(it is NativeObject) { "Property \"arguments\" of config for Engines#fillConfig must be a JavaScript Object" }
                        it.entries.forEach { (key, value) -> result.setArgument(coerceString(key), value) }
                    }
                }
                else -> throw WrappedIllegalArgumentException("Argument \"config\" ${config.jsBrief()} for Engines#fillConfig is invalid")
            }
            return result
        }

    }

}
