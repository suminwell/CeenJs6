package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import android.widget.Chronometer
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class ChronometerInflater<V : Chronometer>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : TextViewInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<Chronometer> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): Chronometer {
            return Chronometer(context)
        }
    }

}
