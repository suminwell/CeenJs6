package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsButton
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsButtonAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ButtonAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsButton

}