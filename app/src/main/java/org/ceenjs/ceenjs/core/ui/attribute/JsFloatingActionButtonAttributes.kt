package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsFloatingActionButton
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsFloatingActionButtonAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : FloatingActionButtonAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsFloatingActionButton

}