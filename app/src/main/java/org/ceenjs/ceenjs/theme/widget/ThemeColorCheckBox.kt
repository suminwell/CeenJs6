package org.ceenjs.ceenjs.theme.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import org.ceenjs.ceenjs.theme.ThemeColor
import org.ceenjs.ceenjs.theme.ThemeColorHelper
import org.ceenjs.ceenjs.theme.ThemeColorManager
import org.ceenjs.ceenjs.theme.ThemeColorMutable

/**
 * Created by SuperMonster003 on May 8, 2025.
 */
class ThemeColorCheckBox : CheckBox, ThemeColorMutable {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        ThemeColorManager.add(this)
    }

    override fun setThemeColor(color: ThemeColor) {
        ThemeColorHelper.setColorPrimary(this, color.colorPrimary, true)
    }

}
