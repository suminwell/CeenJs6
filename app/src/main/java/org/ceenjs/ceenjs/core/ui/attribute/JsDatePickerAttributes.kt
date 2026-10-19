package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsDatePicker
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsDatePickerAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : DatePickerAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsDatePicker

}