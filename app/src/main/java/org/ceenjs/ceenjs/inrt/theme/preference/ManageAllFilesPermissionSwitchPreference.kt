package org.ceenjs.ceenjs.inrt.theme.preference

import android.content.Context
import android.util.AttributeSet
import org.ceenjs.ceenjs.permission.AllFilesAccessPermission
import org.ceenjs.ceenjs.theme.preference.ThemeColorPermissionSwitchPreference

class ManageAllFilesPermissionSwitchPreference : ThemeColorPermissionSwitchPreference {

    val perm by lazy { AllFilesAccessPermission(context) }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun has() = perm.has()

    override fun request() = perm.request()

    override fun revoke() = perm.revoke()

}