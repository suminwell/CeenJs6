package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsFloatingActionButton
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsFloatingActionButtonInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : FloatingActionButtonInflater<JsFloatingActionButton>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsFloatingActionButton> = object : ViewCreator<JsFloatingActionButton> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsFloatingActionButton {
            return JsFloatingActionButton(context)
        }
    }

}