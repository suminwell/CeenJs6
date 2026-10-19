package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.AdapterView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AdapterViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ViewGroupAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as AdapterView<*>

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("selection") { view.setSelection(it.toInt()) }
    }

}
