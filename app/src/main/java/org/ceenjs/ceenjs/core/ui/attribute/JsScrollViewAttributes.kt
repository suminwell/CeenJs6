package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsScrollView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsScrollViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ScrollViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsScrollView

}