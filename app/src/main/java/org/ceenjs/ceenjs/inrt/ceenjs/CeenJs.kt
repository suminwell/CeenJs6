package org.ceenjs.ceenjs.inrt.ceenjs

import android.app.Application
import android.content.Context
import org.ceenjs.ceenjs.inrt.LogActivity
import org.ceenjs.ceenjs.inrt.SettingsActivity
import org.ceenjs.ceenjs.runtime.api.AppUtils
import org.ceenjs.ceenjs.CeenJs as CeenJsApp

/**
 * Created by Stardust on Apr 2, 2017.
 */
class CeenJs(application: Application) : CeenJsApp(application) {

    override fun createAppUtils(context: Context): AppUtils {
        val authorityName = "${context.packageName}.fileprovider"
        return AppUtils(context, authorityName)
    }

    override fun createRuntime() = super.createRuntime().apply {
        putProperty("class.settings", SettingsActivity::class.java)
        putProperty("class.console", LogActivity::class.java)
    }

    companion object {

        val instance
            get() = CeenJsApp.instance

    }

}
