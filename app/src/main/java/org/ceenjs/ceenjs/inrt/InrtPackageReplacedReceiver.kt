package org.ceenjs.ceenjs.inrt

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.ceenjs.ceenjs.AbstractCeenJs

/**
 * Keep packaged app runtime launch configuration dominant after reinstall/update.
 * 在重新安装/更新后保持打包应用运行时启动配置的优先级.
 *
 * Created by JetBrains AI Assistant (GPT-5.3-Codex (xhigh)) on Mar 9, 2026.
 */
class InrtPackageReplacedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (!AbstractCeenJs.isInrt) {
            return
        }
        if (intent?.action != Intent.ACTION_MY_PACKAGE_REPLACED) {
            return
        }
        Pref.syncLaunchConfigWithBuild(force = true)
        InrtShortcuts.syncToExplicitIntents()
    }
}
