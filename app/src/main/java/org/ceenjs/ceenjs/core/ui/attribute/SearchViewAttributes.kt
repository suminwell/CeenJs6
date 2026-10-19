package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.SearchView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.inflaters.TextViewInflater
import org.ceenjs.ceenjs.core.ui.inflater.util.Dimensions
import org.ceenjs.ceenjs.core.ui.inflater.util.Strings
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class SearchViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : LinearLayoutAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as SearchView

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("imeOptions") { view.imeOptions = TextViewInflater.IME_OPTIONS.split(it) }
        registerAttr("inputType") { view.inputType = TextViewInflater.INPUT_TYPES.split(it) }
        registerAttr("maxWidth") { view.maxWidth = Dimensions.parseToIntPixel(it, view) }
        registerAttr("queryHint") { view.queryHint = Strings.parse(view, it) }
        registerAttrs(arrayOf("isIconified", "iconified")) { view.isIconified = it.toBoolean() }
        registerAttrs(arrayOf("isIconifiedByDefault", "iconifiedByDefault")) { view.isIconifiedByDefault = it.toBoolean() }
        registerAttrs(arrayOf("isQueryRefinementEnabled", "queryRefinementEnabled", "isQueryRefinement", "enableQueryRefinement")) { view.isQueryRefinementEnabled = it.toBoolean() }
        registerAttrs(arrayOf("isSubmitButtonEnabled", "submitButtonEnabled", "isSubmitButton", "enableSubmitButton")) { view.isSubmitButtonEnabled = it.toBoolean() }
    }

}
