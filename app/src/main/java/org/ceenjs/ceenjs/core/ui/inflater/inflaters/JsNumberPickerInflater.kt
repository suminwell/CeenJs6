package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsNumberPicker
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsNumberPickerInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : NumberPickerInflater<JsNumberPicker>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsNumberPicker> = object : ViewCreator<JsNumberPicker> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsNumberPicker {
            return JsNumberPicker(context).apply {
                minValue = 0
                maxValue = 9
            }
        }
    }

}