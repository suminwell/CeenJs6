package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import androidx.appcompat.widget.AppCompatSpinner
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AppCompatSpinnerAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : SpinnerAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as AppCompatSpinner

}
