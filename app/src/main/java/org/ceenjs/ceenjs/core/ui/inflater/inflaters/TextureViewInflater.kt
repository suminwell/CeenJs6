package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.TextureView
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class TextureViewInflater<V: TextureView>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser): BaseViewInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<TextureView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): TextureView {
            return TextureView(context)
        }
    }

}
