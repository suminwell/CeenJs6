package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsTextClock
import org.ceenjs.ceenjs.runtime.ScriptRuntime

/**
 * Created by SuperMonster003 on May 23, 2023.
 */
class JsTextClockAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : TextClockAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsTextClock

}