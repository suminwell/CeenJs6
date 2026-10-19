package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import com.makeramen.roundedimageview.RoundedImageView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class RoundedImageViewInflater<V : RoundedImageView>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ImageViewInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<RoundedImageView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): RoundedImageView {
            return RoundedImageView(context)
        }
    }

}
