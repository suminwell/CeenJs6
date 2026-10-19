package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsImageView
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class JsImageViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : RoundedImageViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsImageView

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttrs(arrayOf("isCircle", "circle")) { view.isCircle = true }
    }

}