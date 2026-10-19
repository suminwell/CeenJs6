package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsNumberPicker
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class JsNumberPickerAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : NumberPickerAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsNumberPicker

}