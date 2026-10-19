package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsVideoView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsVideoViewInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : VideoViewInflater<JsVideoView>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsVideoView> = object : ViewCreator<JsVideoView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsVideoView {
            return JsVideoView(context)
        }
    }

}