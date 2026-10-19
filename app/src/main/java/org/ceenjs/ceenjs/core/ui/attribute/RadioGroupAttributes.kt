package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.RadioGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.util.Ids
import org.ceenjs.ceenjs.runtime.ScriptRuntime

/**
 * Created by SuperMonster003 on May 20, 2023.
 */
open class RadioGroupAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : LinearLayoutAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as RadioGroup

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("checkedButton") { view.check(Ids.parse(it)) }
    }

}