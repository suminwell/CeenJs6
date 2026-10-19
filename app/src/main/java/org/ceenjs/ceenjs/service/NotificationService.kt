package org.ceenjs.ceenjs.service

import android.content.Context
import android.content.Intent
import android.provider.Settings
import org.ceenjs.ceenjs.core.notification.NotificationListenerService
import org.ceenjs.ceenjs.ui.main.drawer.ServiceItemHelper
import org.ceenjs.ceenjs.util.IntentUtils.startSafely

class NotificationService(override val context: Context) : ServiceItemHelper {

    override val isRunning: Boolean
        get() = NotificationListenerService.isNotificationListenerEnabled(context)

    override fun start(): Boolean = false.also { config() }

    override fun stop(): Boolean = false.also { config() }

    fun config() {
        Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS).startSafely(context)
    }

}