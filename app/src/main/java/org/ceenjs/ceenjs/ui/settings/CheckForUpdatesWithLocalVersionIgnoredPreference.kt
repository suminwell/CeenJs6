package org.ceenjs.ceenjs.ui.settings

import android.content.Context
import android.util.AttributeSet
import org.ceenjs.ceenjs.network.UpdateChecker
import org.ceenjs.ceenjs.network.UpdateChecker.PromptMode
import org.ceenjs.ceenjs.theme.preference.MaterialPreference

/**
 * Created by SuperMonster003 on Apr 21, 2025.
 */
class CheckForUpdatesWithLocalVersionIgnoredPreference : MaterialPreference {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun onClick() {
        UpdateChecker.Builder(context)
            .setPromptMode(PromptMode.DIALOG)
            .setGitHubMainBranch("master")
            .build()
            .checkNow(true)
        super.onClick()
    }

}
