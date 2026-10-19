package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsGridView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsGridViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : JsListViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsGridView

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("orientation") { (view.layoutManager as? GridLayoutManager)?.orientation = LinearLayoutAttributes.ORIENTATIONS[it] }
        registerAttr("spanCount") { (view.layoutManager as? GridLayoutManager)?.spanCount = it.toInt() }
        registerAttrs(arrayOf("isUsingSpansToEstimateScrollbarDimensions", "usingSpansToEstimateScrollbarDimensions")) { (view.layoutManager as? GridLayoutManager)?.isUsingSpansToEstimateScrollbarDimensions = it.toBoolean() }
    }
}