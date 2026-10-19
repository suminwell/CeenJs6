package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsCardView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsCardViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : CardViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsCardView

}