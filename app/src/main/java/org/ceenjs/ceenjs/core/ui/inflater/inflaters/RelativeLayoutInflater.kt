package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import android.widget.RelativeLayout
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class RelativeLayoutInflater<V : RelativeLayout>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ViewGroupInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<RelativeLayout> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): RelativeLayout {
            return RelativeLayout(context)
        }
    }

}
