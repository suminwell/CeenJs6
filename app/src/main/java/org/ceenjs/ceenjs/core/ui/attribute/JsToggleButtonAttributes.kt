package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsToggleButton
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsToggleButtonAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ToggleButtonAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsToggleButton

}