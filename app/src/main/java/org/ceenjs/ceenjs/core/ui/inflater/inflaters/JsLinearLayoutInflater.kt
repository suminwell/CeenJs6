package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsLinearLayout
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsLinearLayoutInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : LinearLayoutInflater<JsLinearLayout>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsLinearLayout> = object : ViewCreator<JsLinearLayout> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsLinearLayout {
            return JsLinearLayout(context)
        }
    }

}