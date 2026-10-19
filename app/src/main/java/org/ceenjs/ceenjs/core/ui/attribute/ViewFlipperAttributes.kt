package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.ViewFlipper
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class ViewFlipperAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ViewAnimatorAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as ViewFlipper

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttrs(arrayOf("isAutoStart", "autoStart")) { view.isAutoStart = it.toBoolean() }
        registerAttr("flipInterval") { view.flipInterval = it.toInt() }
    }

}
