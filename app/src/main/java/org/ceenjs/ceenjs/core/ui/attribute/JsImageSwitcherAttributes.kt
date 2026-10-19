package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsImageSwitcher
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsImageSwitcherAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ImageSwitcherAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsImageSwitcher

}