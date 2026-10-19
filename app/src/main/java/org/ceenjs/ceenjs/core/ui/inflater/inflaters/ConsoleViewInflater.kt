package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.console.ConsoleView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class ConsoleViewInflater<V: ConsoleView>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser): FrameLayoutInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<ConsoleView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): ConsoleView {
            return ConsoleView(context)
        }
    }

}
