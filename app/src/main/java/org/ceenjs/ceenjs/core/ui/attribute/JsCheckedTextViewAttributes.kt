package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsCheckedTextView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsCheckedTextViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : CheckedTextViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsCheckedTextView

}