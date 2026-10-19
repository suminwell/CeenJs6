package org.ceenjs.ceenjs.core.ui.attribute

import android.view.TextureView
import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class TextureViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as TextureView

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttrs(arrayOf("isOpaque", "opaque")) { view.isOpaque = it.toBoolean() }
    }

}
