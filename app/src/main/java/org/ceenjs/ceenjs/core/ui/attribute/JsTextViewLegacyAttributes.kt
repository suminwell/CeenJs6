package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsTextViewLegacy
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsTextViewLegacyAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : AppCompatTextViewLegacyAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsTextViewLegacy

}