package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsCheckBox
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsCheckBoxInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : AppCompatCheckBoxInflater<JsCheckBox>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsCheckBox> = object : ViewCreator<JsCheckBox> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsCheckBox {
            return JsCheckBox(context)
        }
    }

}