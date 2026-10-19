package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.core.ui.widget.JsActionMenuView
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs6.R
import org.ceenjs.ceenjs6.databinding.JsToolbarBinding

class JsActionMenuViewInflater(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ActionMenuViewInflater<JsActionMenuView>(scriptRuntime, resourceParser) {

    override fun getCreator() = object : ViewCreator<JsActionMenuView> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): JsActionMenuView {
            val amv = View.inflate(context, R.layout.js_actionmenuview, null) as JsActionMenuView

            val toolbar = JsToolbarBinding.inflate(LayoutInflater.from(context)).toolbar

            // FIXME by SuperMonster003 on Jun 7, 2023.
            //  ! Doesn't work.
            //  ! zh-CN: 不起作用.
            @Suppress("DEPRECATION")
            toolbar.overflowIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)

            return amv
        }
    }

}