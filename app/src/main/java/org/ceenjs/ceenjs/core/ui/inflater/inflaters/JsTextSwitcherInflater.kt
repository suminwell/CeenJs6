package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsTextSwitcher
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsTextSwitcherInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : TextSwitcherInflater<JsTextSwitcher>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsTextSwitcher> = object : ViewCreator<JsTextSwitcher> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsTextSwitcher {
            return JsTextSwitcher(context)
        }
    }

}