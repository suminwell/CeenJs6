package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import com.google.android.material.appbar.AppBarLayout
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.util.Dimensions
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.util.ColorUtils

open class AppBarLayoutAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : LinearLayoutAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as AppBarLayout

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("expanded") { view.setExpanded(it.toBoolean()) }
        registerAttrs(arrayOf("isLifted", "lifted")) { view.isLifted = it.toBoolean() }
        registerAttrs(arrayOf("statusBarForegroundColor", "statusBarFgColor")) { view.setStatusBarForegroundColor(ColorUtils.parse(view, it)) }
        @Suppress("DEPRECATION")
        registerAttr("elevation") { view.targetElevation = Dimensions.parseToPixel(it, view) }
    }

}