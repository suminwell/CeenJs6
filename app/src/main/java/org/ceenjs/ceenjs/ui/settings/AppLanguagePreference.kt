package org.ceenjs.ceenjs.ui.settings

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import com.afollestad.materialdialogs.MaterialDialog
import org.ceenjs.ceenjs.app.GlobalAppContext
import org.ceenjs.ceenjs.core.pref.Language
import org.ceenjs.ceenjs.theme.preference.MaterialListPreference
import org.ceenjs.ceenjs.ui.BaseActivity
import org.ceenjs.ceenjs.util.IntentUtils.startSafely
import org.ceenjs.ceenjs.util.LocaleUtils

class AppLanguagePreference : MaterialListPreference {

    @Suppress("unused")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    @Suppress("unused")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @Suppress("unused")
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    @Suppress("unused")
    constructor(context: Context) : super(context)

    override fun onNeutral() {
        Intent(Intent.ACTION_MAIN).apply {
            setClassName("com.android.settings", "com.android.settings.LanguageSettings")
        }.startSafely(prefContext)
    }

    override fun onChangeConfirmed(dialog: MaterialDialog) {
        super.onChangeConfirmed(dialog)
        (@Suppress("EnumValuesSoftDeprecate") Language.values()).find {
            it.getEntryName(prefContext) == dialog.items?.get(dialog.selectedIndex)
        }?.let {
            GlobalAppContext.post {
                when (it.isAuto()) {
                    true -> LocaleUtils.setFollowSystem((prefContext as BaseActivity))
                    else -> LocaleUtils.setLocale(prefContext as BaseActivity, it.locale)
                }
            }
        }
    }

}
