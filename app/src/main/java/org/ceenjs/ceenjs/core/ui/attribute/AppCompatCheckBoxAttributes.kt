package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AppCompatCheckBoxAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : CheckBoxAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as AppCompatCheckBox

}
