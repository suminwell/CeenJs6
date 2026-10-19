package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import android.widget.RadioButton
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

/**
 * Created by SuperMonster003 on May 21, 2023.
 */
open class RadioButtonInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : CompoundButtonInflater<RadioButton>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in RadioButton> = object : ViewCreator<RadioButton> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): RadioButton {
            return RadioButton(context)
        }
    }

}