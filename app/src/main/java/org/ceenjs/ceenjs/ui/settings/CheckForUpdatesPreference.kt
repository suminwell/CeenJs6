package org.ceenjs.ceenjs.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.util.AttributeSet
import org.ceenjs.ceenjs.core.pref.Pref
import org.ceenjs.ceenjs.network.UpdateChecker
import org.ceenjs.ceenjs.network.UpdateChecker.PromptMode
import org.ceenjs.ceenjs.theme.preference.MaterialPreference
import org.ceenjs.ceenjs6.R

/**
 * Created by SuperMonster003 on May 31, 2022.
 */
class CheckForUpdatesPreference : MaterialPreference, OnSharedPreferenceChangeListener {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context) : super(context)

    init {
        Pref.registerOnSharedPreferenceChangeListener(this)
        summaryProvider = SummaryProvider<CheckForUpdatesPreference> {
            Pref.lastUpdatesCheckedTimeString?.let { prefContext.getString(R.string.text_last_updates_checked_time, it) }
        }
    }

    override fun onClick() {
        UpdateChecker.Builder(prefContext)
            .setPromptMode(PromptMode.DIALOG)
            .setGitHubMainBranch("master")
            .build().checkNow()
        super.onClick()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) = notifyChanged()

}