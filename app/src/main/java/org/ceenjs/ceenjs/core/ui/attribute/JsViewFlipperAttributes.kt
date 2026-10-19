package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsViewFlipper
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsViewFlipperAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ViewFlipperAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsViewFlipper

}