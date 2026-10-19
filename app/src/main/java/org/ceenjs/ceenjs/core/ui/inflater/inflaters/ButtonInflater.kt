package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class ButtonInflater<V : Button>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : TextViewInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<Button> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): Button {
            return Button(context)
        }
    }

}
