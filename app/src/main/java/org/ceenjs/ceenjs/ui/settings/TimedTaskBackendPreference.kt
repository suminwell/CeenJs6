package org.ceenjs.ceenjs.ui.settings

import android.content.Context
import android.util.AttributeSet
import com.afollestad.materialdialogs.MaterialDialog
import org.ceenjs.ceenjs.theme.preference.MaterialListPreference
import org.ceenjs.ceenjs.timing.ExactAlarmPermissionHelper
import org.ceenjs.ceenjs.timing.TimedTaskScheduler
import org.ceenjs.ceenjs6.R

class TimedTaskBackendPreference : MaterialListPreference {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun onChangeConfirmed(dialog: MaterialDialog) {
        super.onChangeConfirmed(dialog)

        when (dialog.items?.get(dialog.selectedIndex)?.toString()) {
            prefContext.getString(R.string.entry_timed_task_backend_alarm) -> {
                ExactAlarmPermissionHelper.checkExactAlarmPermission(prefContext)
            }
        }
        TimedTaskScheduler.init(prefContext)
    }

}