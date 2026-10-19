package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsChronometer
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsChronometerInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ChronometerInflater<JsChronometer>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsChronometer> = object : ViewCreator<JsChronometer> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsChronometer {
            return JsChronometer(context)
        }
    }

}