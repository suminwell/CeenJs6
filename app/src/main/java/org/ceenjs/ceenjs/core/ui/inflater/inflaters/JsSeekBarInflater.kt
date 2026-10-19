package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsSeekBar
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsSeekBarInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : SeekBarInflater<JsSeekBar>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsSeekBar> = object : ViewCreator<JsSeekBar> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsSeekBar {
            return JsSeekBar(context)
        }
    }

}