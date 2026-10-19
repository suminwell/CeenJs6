package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsSwitch
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsSwitchInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : SwitchCompatInflater<JsSwitch>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsSwitch> = object : ViewCreator<JsSwitch> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsSwitch {
            return JsSwitch(context)
        }
    }

}