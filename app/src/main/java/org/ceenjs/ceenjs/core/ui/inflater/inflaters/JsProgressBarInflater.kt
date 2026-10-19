package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.R
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.inflater.util.Res
import org.ceenjs.ceenjs.core.ui.widget.JsProgressBar
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsProgressBarInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ProgressBarInflater<JsProgressBar>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsProgressBar> = object : ViewCreator<JsProgressBar> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsProgressBar {
            fun hasTrueAttr(name: String) = attrs["android:$name"] == "true"

            attrs["style"]?.let { return JsProgressBar(context, null, 0, Res.parseStyle(context, it)) }

            if (hasTrueAttr("isHorizontal") || hasTrueAttr("horizontal")) {
                return JsProgressBar(context, null, 0, R.style.Base_Widget_AppCompat_ProgressBar_Horizontal)
            }
            return JsProgressBar(context)
        }
    }

}