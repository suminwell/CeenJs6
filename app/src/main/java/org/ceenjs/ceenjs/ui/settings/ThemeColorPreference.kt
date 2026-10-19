package org.ceenjs.ceenjs.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.util.AttributeSet
import org.ceenjs.ceenjs.core.pref.Pref
import org.ceenjs.ceenjs.theme.app.ColorSelectBaseActivity
import org.ceenjs.ceenjs.theme.preference.MaterialPreference

class ThemeColorPreference : MaterialPreference, SharedPreferences.OnSharedPreferenceChangeListener {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context) : super(context)

    init {
        Pref.registerOnSharedPreferenceChangeListener(this)
        summaryProvider = SummaryProvider<ThemeColorPreference> { ColorSelectBaseActivity.getCurrentColorSummary(prefContext) }
    }

    override fun onClick() {
        super.onClick()
        ColorSelectBaseActivity.startActivity(prefContext)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) = notifyChanged()

}
