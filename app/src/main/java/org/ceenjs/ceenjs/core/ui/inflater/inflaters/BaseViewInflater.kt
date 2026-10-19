package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.view.View
import android.view.ViewGroup
import org.ceenjs.ceenjs.core.ui.ViewExtras
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.util.Drawables
import org.ceenjs.ceenjs.runtime.ScriptRuntime

/**
 * Created by Stardust on Nov 3, 2017.
 * Modified by SuperMonster003 as of Dec 5, 2021.
 * Transformed by SuperMonster003 on Apr 12, 2023.
 */
open class BaseViewInflater<V : View>(private val scriptRuntime: ScriptRuntime, val resourceParser: ResourceParser) : ViewInflater<V> {

    val drawables: Drawables
        get() = resourceParser.drawables

    override fun setAttr(view: V, attrName: String, value: String, parent: ViewGroup?): Boolean {
        return ViewExtras.getViewAttributes(scriptRuntime, view, resourceParser)[attrName]?.apply { set(value) } != null
    }

}