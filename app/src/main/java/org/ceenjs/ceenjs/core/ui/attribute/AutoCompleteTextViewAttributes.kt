package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.AutoCompleteTextView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AutoCompleteTextViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : EditTextAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as AutoCompleteTextView

}
