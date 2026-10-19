package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsCheckBox
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsCheckBoxAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : AppCompatCheckBoxAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsCheckBox

}