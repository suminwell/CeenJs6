package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsQuickContactBadge
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsQuickContactBadgeAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : QuickContactBadgeAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsQuickContactBadge

}