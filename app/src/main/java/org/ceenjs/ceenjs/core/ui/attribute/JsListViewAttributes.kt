package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsListView
import org.ceenjs.ceenjs.groundwork.WrapContentLinearLayoutManager
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class JsListViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : RecyclerViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsListView

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("orientation") { view.layoutManager = WrapContentLinearLayoutManager(view.context, LinearLayoutAttributes.ORIENTATIONS[it], false) }
    }

}