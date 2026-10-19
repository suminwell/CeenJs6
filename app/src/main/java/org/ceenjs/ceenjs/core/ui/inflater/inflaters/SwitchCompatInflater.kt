package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class SwitchCompatInflater<V : SwitchCompat>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : CompoundButtonInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<SwitchCompat> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): SwitchCompat {
            return SwitchCompat(context)
        }
    }

}
