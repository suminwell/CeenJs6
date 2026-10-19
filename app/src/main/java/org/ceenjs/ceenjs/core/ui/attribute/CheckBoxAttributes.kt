package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.CheckBox
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class CheckBoxAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : CompoundButtonAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as CheckBox

}
