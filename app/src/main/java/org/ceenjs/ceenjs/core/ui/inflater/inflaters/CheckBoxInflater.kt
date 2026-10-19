package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import android.widget.CheckBox
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class CheckBoxInflater<V: CheckBox>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser): CompoundButtonInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<CheckBox> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): CheckBox {
            return CheckBox(context)
        }
    }

}
