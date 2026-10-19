package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsImageButton
import org.ceenjs.ceenjs.runtime.ScriptRuntime

/**
 * Created by SuperMonster003 on May 23, 2023.
 */
class JsImageButtonInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ImageButtonInflater<JsImageButton>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsImageButton> = object : ViewCreator<JsImageButton> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsImageButton {
            return JsImageButton(context)
        }
    }

}