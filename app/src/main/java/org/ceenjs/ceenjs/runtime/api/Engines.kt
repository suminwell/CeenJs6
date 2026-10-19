package org.ceenjs.ceenjs.runtime.api

import org.ceenjs.ceenjs.CeenJs
import org.ceenjs.ceenjs.engine.JavaScriptEngine
import org.ceenjs.ceenjs.engine.ScriptEngine
import org.ceenjs.ceenjs.execution.ExecutionConfig
import org.ceenjs.ceenjs.execution.ScriptExecution
import org.ceenjs.ceenjs.rhino.extension.IterableExtensions.toNativeArray
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.script.AutoFileSource
import org.ceenjs.ceenjs.script.JavaScriptFileSource
import org.ceenjs.ceenjs.script.ScriptSource
import org.ceenjs.ceenjs.script.StringScriptSource
import org.mozilla.javascript.NativeArray

/**
 * Created by Stardust on Aug 4, 2017.
 * Transformed by SuperMonster003 on Oct 26, 2024.
 */
class Engines(private val mScriptRuntime: ScriptRuntime) {

    private lateinit var mScriptEngine: JavaScriptEngine

    val engines: Set<ScriptEngine<out ScriptSource?>>
        get() = CeenJs.instance.scriptEngineService.engines

    fun setCurrentEngine(engine: JavaScriptEngine) {
        check(!::mScriptEngine.isInitialized)
        mScriptEngine = engine
    }

    fun myEngine(): JavaScriptEngine = mScriptEngine

    fun execScript(name: String, script: String, config: ExecutionConfig): ScriptExecution {
        return execScriptInternal(name, script, config)
    }

    fun execScriptFile(path: String, config: ExecutionConfig?): ScriptExecution {
        return CeenJs.instance.scriptEngineService.execute(JavaScriptFileSource(mScriptRuntime.files.nonNullPath(path)), config)
    }

    fun execAutoFile(path: String, config: ExecutionConfig?): ScriptExecution {
        return CeenJs.instance.scriptEngineService.execute(AutoFileSource(mScriptRuntime.files.nonNullPath(path)), config)
    }

    fun all(): NativeArray = engines.toNativeArray()

    fun stopAll(): Int = CeenJs.instance.scriptEngineService.stopAll()

    fun stopAllAndToast(): Int = CeenJs.instance.scriptEngineService.stopAllAndToast()

    companion object {

        private fun execScriptInternal(name: String, script: String, config: ExecutionConfig): ScriptExecution {
            val scriptSource = StringScriptSource(name, script).apply { prefix = "\$engine/" }
            return CeenJs.instance.scriptEngineService.execute(scriptSource, config)
        }

    }

}
