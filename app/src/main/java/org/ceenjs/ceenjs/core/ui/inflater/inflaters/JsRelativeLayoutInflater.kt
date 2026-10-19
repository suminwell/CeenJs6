package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsRelativeLayout
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsRelativeLayoutInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : RelativeLayoutInflater<JsRelativeLayout>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsRelativeLayout> = object : ViewCreator<JsRelativeLayout> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsRelativeLayout {
            return JsRelativeLayout(context)
        }
    }

}