package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsSearchView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsSearchViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : SearchViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsSearchView

}