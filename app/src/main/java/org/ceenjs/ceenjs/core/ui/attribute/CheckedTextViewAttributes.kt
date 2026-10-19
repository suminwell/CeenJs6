package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.CheckedTextView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.util.ColorUtils

open class CheckedTextViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : TextViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as CheckedTextView

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("checkMarkDrawable") { view.checkMarkDrawable = drawables.parse(view, it) }
        registerAttrs(arrayOf("checkMarkTintList", "checkMarkTint")) { view.checkMarkTintList = ColorUtils.toColorStateList(view, it) }
    }

}
