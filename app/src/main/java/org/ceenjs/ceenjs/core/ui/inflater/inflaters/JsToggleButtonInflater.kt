package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsToggleButton
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsToggleButtonInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ToggleButtonInflater<JsToggleButton>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsToggleButton> = object : ViewCreator<JsToggleButton> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsToggleButton {
            return JsToggleButton(context)
        }
    }

}