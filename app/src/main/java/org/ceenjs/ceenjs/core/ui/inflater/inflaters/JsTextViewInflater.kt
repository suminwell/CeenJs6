package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsTextView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsTextViewInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : AppCompatTextViewInflater<JsTextView>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in AppCompatTextView> = object : ViewCreator<AppCompatTextView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsTextView {
            return JsTextView(context)
        }
    }

}