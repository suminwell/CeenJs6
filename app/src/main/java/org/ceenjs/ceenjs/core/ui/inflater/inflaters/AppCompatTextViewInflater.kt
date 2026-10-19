package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AppCompatTextViewInflater<V : AppCompatTextView>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : TextViewInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in AppCompatTextView> = object : ViewCreator<AppCompatTextView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): AppCompatTextView {
            return AppCompatTextView(context)
        }
    }

}
