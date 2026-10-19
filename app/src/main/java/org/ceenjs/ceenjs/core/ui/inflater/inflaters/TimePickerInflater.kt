package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs6.R

/**
 * Created by Stardust on Nov 29, 2017.
 * Transformed by SuperMonster003 on May 23, 2023.
 */
open class TimePickerInflater<V : TimePicker>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : FrameLayoutInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> {
        return object : ViewCreator<TimePicker> {
            override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): TimePicker {
                return attrs.remove("android:timePickerMode")?.takeUnless { it == "spinner" }?.let {
                    TimePicker(context)
                } ?: View.inflate(context, R.layout.time_picker_spinner, null) as TimePicker
            }
        }
    }

}