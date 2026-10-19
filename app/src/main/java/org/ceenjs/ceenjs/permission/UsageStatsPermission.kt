package org.ceenjs.ceenjs.permission

import android.content.Context
import org.ceenjs.ceenjs.app.AppOps.isUsageStatsPermissionGranted
import org.ceenjs.ceenjs.ui.main.drawer.PermissionItemHelper
import org.ceenjs.ceenjs.util.IntentUtils

class UsageStatsPermission(override val context: Context) : PermissionItemHelper {

    override fun has() = isUsageStatsPermissionGranted(context)

    override fun request() = false.also { config() }

    override fun revoke() = false.also { config() }

    fun config() = IntentUtils.requestAppUsagePermission(context)

}