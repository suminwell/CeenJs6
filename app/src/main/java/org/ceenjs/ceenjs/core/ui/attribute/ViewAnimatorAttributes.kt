package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.ViewAnimator
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class ViewAnimatorAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : FrameLayoutAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as ViewAnimator

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("animateFirstView") { view.animateFirstView = it.toBoolean() }
        registerAttr("displayedChild") { view.displayedChild = it.toInt() }
    }

}
