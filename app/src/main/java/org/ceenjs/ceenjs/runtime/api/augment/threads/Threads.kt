package org.ceenjs.ceenjs.runtime.api.augment.threads

import org.ceenjs.ceenjs.annotation.RhinoRuntimeFunctionInterface
import org.ceenjs.ceenjs.core.looper.TimerThread
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.jsBrief
import org.ceenjs.ceenjs.rhino.ArgumentGuards
import org.ceenjs.ceenjs.rhino.extension.ScriptableObjectExtensions.inquire
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.exception.ScriptInterruptedException
import org.ceenjs.ceenjs.util.RhinoUtils.UNDEFINED
import org.ceenjs.ceenjs.util.RhinoUtils.coerceIntNumber
import org.ceenjs.ceenjs.util.RhinoUtils.coerceLongNumber
import org.ceenjs.ceenjs.util.RhinoUtils.coerceObject
import org.ceenjs.ceenjs.util.RhinoUtils.coerceRunnable
import org.ceenjs.ceenjs.util.RhinoUtils.undefined
import org.ceenjs.ceenjs6.R
import org.mozilla.javascript.BaseFunction
import org.mozilla.javascript.NativeObject
import org.mozilla.javascript.Synchronizer
import org.mozilla.javascript.Undefined
import java.util.concurrent.ThreadPoolExecutor

@Suppress("unused", "UNUSED_PARAMETER")
class Threads(scriptRuntime: ScriptRuntime) : Augmentable(scriptRuntime) {

    override val selfAssignmentFunctions = listOf(
        ::interrupt.name,
        ::start.name,
        ::pool.name,
        ::disposable.name,
    )

    override val globalAssignmentFunctions = listOf(
        ::sync.name,
    )

    companion object : ArgumentGuards() {

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun interrupt(scriptRuntime: ScriptRuntime, args: Array<out Any?>): Undefined = ensureArgumentsOnlyOne(args) {
            undefined {
                if (it is TimerThread && it.isAlive) it.interrupt()
            }
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun start(scriptRuntime: ScriptRuntime, args: Array<out Any?>): Any = ensureArgumentsOnlyOne(args) {
            try {
                scriptRuntime.threads.start(coerceRunnable(scriptRuntime, it))
            } catch (e: Exception) {
                if (!ScriptInterruptedException.causedByInterrupt(Throwable(e))) {
                    if (e.message?.endsWith(globalContext.getString(R.string.error_script_is_on_exiting)) != true) {
                        throw RuntimeException("${e}\n${e.stackTrace}")
                    }
                }
                UNDEFINED
            }
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun pool(scriptRuntime: ScriptRuntime, args: Array<out Any?>): ThreadPoolExecutor = ensureArgumentsAtMost(args, 1) { argList ->
            val (options) = argList
            val opt = coerceObject(options, NativeObject())
            scriptRuntime.threads.internalPool(
                opt.inquire("corePoolSize", ::coerceIntNumber, 0),
                opt.inquire("maxPoolSize", ::coerceIntNumber, 0),
                opt.inquire("keepAliveTime", ::coerceLongNumber, 60000L),
            )
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun sync(scriptRuntime: ScriptRuntime, args: Array<out Any?>): Synchronizer = ensureArgumentsLengthInRange(args, 1..2) {
            val (func, lock) = it
            require(func is BaseFunction) { "Argument \"func\" ${func.jsBrief()} for global.sync must be a JavaScript Function" }
            Synchronizer(func, lock)
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun disposable(scriptRuntime: ScriptRuntime, args: Array<out Any?>): VolatileDisposeNativeObject = ensureArgumentsIsEmpty(args) {
            VolatileDisposeNativeObject()
        }

    }

}
