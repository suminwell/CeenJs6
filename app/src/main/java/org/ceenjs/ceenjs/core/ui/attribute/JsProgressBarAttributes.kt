package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsProgressBar
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsProgressBarAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ProgressBarAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsProgressBar

}