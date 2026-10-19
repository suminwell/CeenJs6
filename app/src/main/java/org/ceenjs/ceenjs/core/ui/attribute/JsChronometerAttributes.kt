package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsChronometer
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsChronometerAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ChronometerAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsChronometer

}