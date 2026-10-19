package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.widget.AbsSeekBar
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AbsSeekBarInflater<V : AbsSeekBar>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ProgressBarInflater<V>(scriptRuntime, resourceParser) {
    // Empty inflater.
}
