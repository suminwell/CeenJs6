package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.ViewSwitcher
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class ViewSwitcherAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ViewAnimatorAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as ViewSwitcher

}
