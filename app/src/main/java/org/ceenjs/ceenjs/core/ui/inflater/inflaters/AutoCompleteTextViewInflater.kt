package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AutoCompleteTextViewInflater<V : AutoCompleteTextView>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : EditTextInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<AutoCompleteTextView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): AutoCompleteTextView {
            return AutoCompleteTextView(context)
        }
    }

}
