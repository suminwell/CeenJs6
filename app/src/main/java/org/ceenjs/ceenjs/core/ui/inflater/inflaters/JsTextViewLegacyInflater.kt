package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import androidx.appcompatlegacy.widget.AppCompatTextView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsTextViewLegacy
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsTextViewLegacyInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : TextViewLegacyInflater<JsTextViewLegacy>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in AppCompatTextView> = object : ViewCreator<AppCompatTextView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsTextViewLegacy {
            return JsTextViewLegacy(context)
        }
    }

}