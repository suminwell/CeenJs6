package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageButton
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class ImageButtonInflater<V : ImageButton>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ImageViewInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<ImageButton> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): ImageButton {
            return ImageButton(context)
        }
    }

}
