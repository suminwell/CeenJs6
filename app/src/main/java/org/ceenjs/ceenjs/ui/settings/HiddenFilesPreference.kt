package org.ceenjs.ceenjs.ui.settings

import android.content.Context
import android.util.AttributeSet
import com.afollestad.materialdialogs.MaterialDialog
import org.ceenjs.ceenjs.theme.preference.MaterialListPreference
import org.ceenjs.ceenjs.util.ViewUtils
import org.ceenjs.ceenjs6.R

class HiddenFilesPreference : MaterialListPreference {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun onChangeConfirmed(dialog: MaterialDialog) {
        ViewUtils.showToast(prefContext, R.string.text_refresh_explorer_may_needed, true)
        super.onChangeConfirmed(dialog)
    }

}