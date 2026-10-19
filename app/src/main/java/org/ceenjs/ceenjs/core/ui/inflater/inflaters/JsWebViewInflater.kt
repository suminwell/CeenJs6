package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsWebView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

/**
 * Created by SuperMonster003 on May 15, 2023.
 * Transformed by SuperMonster003 on May 20, 2023.
 */
class JsWebViewInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : WebViewInflater<JsWebView>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsWebView> = object : ViewCreator<JsWebView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsWebView {
            return JsWebView(context)
        }
    }

}