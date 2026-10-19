package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import android.widget.QuickContactBadge
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class QuickContactBadgeInflater<V : QuickContactBadge>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ImageViewInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<QuickContactBadge> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): QuickContactBadge {
            return QuickContactBadge(context)
        }
    }

}
