package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsCardView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsCardViewInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : CardViewInflater<JsCardView>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsCardView> = object : ViewCreator<JsCardView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsCardView {
            return JsCardView(context)
        }
    }

}