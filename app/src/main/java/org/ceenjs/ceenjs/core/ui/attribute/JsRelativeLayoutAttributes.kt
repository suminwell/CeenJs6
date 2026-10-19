package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsRelativeLayout
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsRelativeLayoutAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : RelativeLayoutAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsRelativeLayout

}