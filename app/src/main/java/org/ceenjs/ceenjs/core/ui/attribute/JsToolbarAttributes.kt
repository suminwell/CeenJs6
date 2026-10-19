package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsToolbar
import org.ceenjs.ceenjs.runtime.ScriptRuntime

/**
 * Created by SuperMonster003 on May 20, 2023.
 */
class JsToolbarAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ToolbarAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsToolbar

}