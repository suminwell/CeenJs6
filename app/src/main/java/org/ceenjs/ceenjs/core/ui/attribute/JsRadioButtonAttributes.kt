package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsRadioButton
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsRadioButtonAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : RadioButtonAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsRadioButton

}