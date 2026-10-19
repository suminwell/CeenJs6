package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsDrawerLayout
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsDrawerLayoutAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : DrawerLayoutAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsDrawerLayout

}