package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsImageButton
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsImageButtonAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ImageButtonAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsImageButton

}