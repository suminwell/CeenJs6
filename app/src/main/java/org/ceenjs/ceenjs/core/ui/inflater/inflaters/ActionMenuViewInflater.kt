package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import android.widget.ActionMenuView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class ActionMenuViewInflater<V : ActionMenuView>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : LinearLayoutInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<ActionMenuView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): ActionMenuView {
            return ActionMenuView(context)
        }
    }

}
