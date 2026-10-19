package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.TextSwitcher
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.util.Strings
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class TextSwitcherAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ViewSwitcherAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as TextSwitcher

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttrs(arrayOf("text", "nextText")) { view.setText(Strings.parse(view, it)) }
        registerAttr("currentText") { view.setCurrentText(Strings.parse(view, it)) }
    }

}
