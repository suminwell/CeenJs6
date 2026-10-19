package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsQuickContactBadge
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs6.R

class JsQuickContactBadgeInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : QuickContactBadgeInflater<JsQuickContactBadge>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsQuickContactBadge> = object : ViewCreator<JsQuickContactBadge> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsQuickContactBadge {
            return (View.inflate(context, R.layout.js_quickcontactbadge, null) as JsQuickContactBadge).apply {
                setImageToDefault()
            }
        }
    }

}