package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.widget.JsConsoleView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsConsoleViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ConsoleViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsConsoleView

}