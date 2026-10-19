package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.R
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.inflater.util.Res
import org.ceenjs.ceenjs.core.ui.widget.JsButton
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsButtonInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ButtonInflater<JsButton>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsButton> = object : ViewCreator<JsButton> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsButton {
            fun hasTrueAttr(name: String) = attrs["android:$name"] == "true"

            attrs["style"]?.let { return JsButton(context, null, 0, Res.parseStyle(context, it)) }

            if (hasTrueAttr("isBorderlessColored") || hasTrueAttr("isColoredBorderless")) {
                return JsButton(context, null, 0, R.style.Widget_AppCompat_Button_Borderless_Colored)
            }
            if (hasTrueAttr("isColored")) {
                if (hasTrueAttr("isBorderless")) {
                    return JsButton(context, null, 0, R.style.Widget_AppCompat_Button_Borderless_Colored)
                }
                return JsButton(context, null, 0, R.style.Widget_AppCompat_Button_Colored)
            }
            if (hasTrueAttr("isBorderless")) {
                return JsButton(context, null, 0, R.style.Widget_AppCompat_Button_Borderless)
            }
            return JsButton(context)
        }
    }

}