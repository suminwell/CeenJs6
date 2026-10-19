package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsRadioGroup
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsRadioGroupInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : RadioGroupInflater<JsRadioGroup>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsRadioGroup> = object : ViewCreator<JsRadioGroup> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsRadioGroup {
            return JsRadioGroup(context)
        }
    }

}