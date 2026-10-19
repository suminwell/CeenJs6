package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsWebView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

/**
 * Created by SuperMonster003 on May 20, 2023.
 */
class JsWebViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : WebViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsWebView

}