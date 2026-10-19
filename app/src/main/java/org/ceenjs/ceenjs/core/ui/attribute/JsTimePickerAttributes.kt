package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsTimePicker
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsTimePickerAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : TimePickerAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsTimePicker

}