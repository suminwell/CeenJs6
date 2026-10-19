package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsTextView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsTextViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : AppCompatTextViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsTextView

}