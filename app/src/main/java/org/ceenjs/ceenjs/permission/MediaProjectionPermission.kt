package org.ceenjs.ceenjs.permission

import android.content.Context
import org.ceenjs.ceenjs.app.AppOps.isProjectMediaAccessGranted
import org.ceenjs.ceenjs.permission.Base.PROJECT_MEDIA_PERMISSION
import org.ceenjs.ceenjs.ui.main.drawer.CommandBasedPermissionItemHelper
import org.ceenjs.ceenjs.ui.main.drawer.IPermissionItem.Companion.ACTION

class MediaProjectionPermission(override val context: Context) : CommandBasedPermissionItemHelper {

    override fun has() = isProjectMediaAccessGranted(context)

    override fun getCommand(action: ACTION): String {
        val actionString = when (action) {
            ACTION.REQUEST -> "allow"
            ACTION.REVOKE -> "ignore"
        }
        return "appops set ${context.packageName} $PROJECT_MEDIA_PERMISSION $actionString"
    }

}