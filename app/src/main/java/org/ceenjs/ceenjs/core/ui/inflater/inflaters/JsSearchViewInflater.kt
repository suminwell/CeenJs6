package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsSearchView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsSearchViewInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : SearchViewInflater<JsSearchView>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsSearchView> = object : ViewCreator<JsSearchView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsSearchView {
            return JsSearchView(context)
        }
    }

}