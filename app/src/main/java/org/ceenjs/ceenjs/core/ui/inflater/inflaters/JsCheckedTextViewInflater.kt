package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.widget.JsCheckedTextView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsCheckedTextViewInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : CheckedTextViewInflater<JsCheckedTextView>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsCheckedTextView> = object : ViewCreator<JsCheckedTextView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsCheckedTextView {
            return JsCheckedTextView(context)
        }
    }

}