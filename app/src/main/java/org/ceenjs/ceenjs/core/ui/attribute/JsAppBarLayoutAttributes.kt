package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsAppBarLayout
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsAppBarLayoutAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : AppBarLayoutAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsAppBarLayout

}