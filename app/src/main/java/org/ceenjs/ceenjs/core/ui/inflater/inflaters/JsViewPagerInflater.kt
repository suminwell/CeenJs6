package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsViewPager
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsViewPagerInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ViewPagerInflater<JsViewPager>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in JsViewPager> = object : ViewCreator<JsViewPager> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsViewPager {
            return JsViewPager(context)
        }
    }

}