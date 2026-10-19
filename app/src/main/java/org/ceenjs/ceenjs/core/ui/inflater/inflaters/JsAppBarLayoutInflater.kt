package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsAppBarLayout
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs6.R

class JsAppBarLayoutInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : AppBarLayoutInflater<JsAppBarLayout>(scriptRuntime, resourceParser) {

    override fun getCreator() = object : ViewCreator<JsAppBarLayout> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsAppBarLayout {
            return View.inflate(context, R.layout.js_appbar, null) as JsAppBarLayout
        }
    }

}