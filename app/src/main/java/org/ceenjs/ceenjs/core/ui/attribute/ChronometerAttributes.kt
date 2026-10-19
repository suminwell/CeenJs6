package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.Chronometer
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.util.Strings
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class ChronometerAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : TextViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as Chronometer

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("base") { view.base = it.toLong() }
        registerAttrs(arrayOf("isCountDown", "countDown")) { view.isCountDown = it.toBoolean() }
        registerAttr("format") { view.format = Strings.parse(view, it) }
        registerAttrs(arrayOf("autoStart", "isAutoStart")) { if (it.toBoolean()) view.start() }
    }

}
