package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.SeekBar
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class SeekBarAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : AbsSeekBarAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as SeekBar

}
