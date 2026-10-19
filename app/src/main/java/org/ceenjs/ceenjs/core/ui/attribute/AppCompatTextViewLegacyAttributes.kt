package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import androidx.appcompatlegacy.widget.AppCompatTextView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AppCompatTextViewLegacyAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : TextViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as AppCompatTextView

}
