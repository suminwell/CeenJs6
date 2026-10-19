package org.ceenjs.ceenjs.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.util.AttributeSet
import com.afollestad.materialdialogs.MaterialDialog
import org.ceenjs.ceenjs.core.pref.Pref
import org.ceenjs.ceenjs.theme.preference.MaterialListPreference
import org.ceenjs.ceenjs.util.ViewUtils
import org.ceenjs.ceenjs6.R

class KeepScreenOnWhenInForegroundPreference : MaterialListPreference, SharedPreferences.OnSharedPreferenceChangeListener {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context) : super(context)

    init {
        Pref.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onChangeConfirmed(dialog: MaterialDialog) {
        super.onChangeConfirmed(dialog)

        when (dialog.items?.get(dialog.selectedIndex)?.toString()) {
            prefContext.getString(R.string.entry_keep_screen_on_when_in_foreground_disabled) -> {
                ViewUtils.setKeepScreenOnWhenInForegroundDisabled()
            }
            prefContext.getString(R.string.entry_keep_screen_on_when_in_foreground_all_pages) -> {
                ViewUtils.setKeepScreenOnWhenInForegroundAllPages()
            }
            prefContext.getString(R.string.entry_keep_screen_on_when_in_foreground_homepage_only) -> {
                ViewUtils.setKeepScreenOnWhenInForegroundHomepageOnly()
            }
            else -> Unit
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) = notifyChanged()

}
