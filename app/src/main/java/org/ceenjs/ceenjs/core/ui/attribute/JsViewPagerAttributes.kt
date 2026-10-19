package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.widget.JsViewPager
import org.ceenjs.ceenjs.runtime.ScriptRuntime

class JsViewPagerAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ViewPagerAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsViewPager

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("titles") {
            view.setTitles(parseAttrValue(it).toTypedArray())
            view.adapter?.notifyDataSetChanged()
        }
    }

}