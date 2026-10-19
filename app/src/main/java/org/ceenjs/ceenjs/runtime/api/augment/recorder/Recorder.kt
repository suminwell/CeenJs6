package org.ceenjs.ceenjs.runtime.api.augment.recorder

import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.api.augment.Invokable

class Recorder(private val scriptRuntime: ScriptRuntime) : Augmentable(scriptRuntime), Invokable {

    override fun invoke(vararg args: Any?): Double = ensureArgumentsAtMost(args, 2) {
        val (key, timestamp) = it
        scriptRuntime.recorder.shortcut(key, timestamp)
    }

}