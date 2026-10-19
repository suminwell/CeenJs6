package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.widget.JsCanvasView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsCanvasViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : TextureViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsCanvasView

}