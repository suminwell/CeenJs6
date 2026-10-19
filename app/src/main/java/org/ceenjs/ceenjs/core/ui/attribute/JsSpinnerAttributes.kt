package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.inflaters.TextViewInflater
import org.ceenjs.ceenjs.core.ui.inflater.util.Dimensions
import org.ceenjs.ceenjs.core.ui.widget.JsSpinner
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.util.ColorUtils

/**
 * Created by SuperMonster003 on May 20, 2023.
 */
class JsSpinnerAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : AppCompatSpinnerAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as JsSpinner

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("entries", ::setAdapter)
        registerAttr("entryTextColor") { view.entryTextColor = ColorUtils.parse(view.context, it) }
        registerAttr("entryTextSize") { view.entryTextSize = Dimensions.parseToPixel(it, view) }
        registerAttr("entryTextStyle") { view.entryTextStyle = TextViewInflater.TEXT_STYLES.split(it) }
        registerAttr("textColor") { view.textColor = ColorUtils.parse(view.context, it) }
        registerAttr("textSize") { view.textSize = Dimensions.parseToPixel(it, view) }
        registerAttr("textStyle") { view.textStyle = TextViewInflater.TEXT_STYLES.split(it) }

        registerAttrUnsupported(
            arrayOf(
                "dropDownSelector"
            )
        )
    }

    private fun setAdapter(entries: String) {
        view.adapter = view.Adapter(
            view.context,
            android.R.layout.simple_spinner_dropdown_item,
            entries.split("[|]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        )
    }

}