package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsSeekBar
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsSeekBarAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : SeekBarAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsSeekBar

}
