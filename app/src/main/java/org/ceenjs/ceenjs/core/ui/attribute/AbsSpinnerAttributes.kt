package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.AbsSpinner
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AbsSpinnerAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : AdapterViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as AbsSpinner

}
