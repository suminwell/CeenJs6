package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsCalendarView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsCalendarViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : CalendarViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsCalendarView

}