package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsSwitch
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsSwitchAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : SwitchCompatAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsSwitch

}