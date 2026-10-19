package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import android.widget.RatingBar
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class RatingBarInflater<V : RatingBar>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : AbsSeekBarInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<RatingBar> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): RatingBar {
            return RatingBar(context)
        }
    }

}
