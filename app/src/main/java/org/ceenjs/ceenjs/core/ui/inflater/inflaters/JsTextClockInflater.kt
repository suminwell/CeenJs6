package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsTextClock
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs6.R

/**
 * Transformed by SuperMonster003 on May 20, 2023.
 */
class JsTextClockInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : TextClockInflater<JsTextClock>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsTextClock> = object : ViewCreator<JsTextClock> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsTextClock {
            return View.inflate(context, R.layout.js_textclock, null) as JsTextClock
        }
    }

}