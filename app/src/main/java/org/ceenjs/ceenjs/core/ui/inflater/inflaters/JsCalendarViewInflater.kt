package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsCalendarView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsCalendarViewInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : CalendarViewInflater<JsCalendarView>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsCalendarView> = object : ViewCreator<JsCalendarView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsCalendarView {
            return JsCalendarView(context)
        }
    }

}