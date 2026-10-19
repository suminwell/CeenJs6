package org.ceenjs.ceenjs.util

import android.content.Context
import org.ceenjs.ceenjs.app.GlobalAppContext
import org.ceenjs.ceenjs.ui.log.LogActivity

object ConsoleUtils {

    @JvmStatic
    @JvmOverloads
    fun launch(context: Context? = GlobalAppContext.get()) = try {
        context?.let { LogActivity.launch(it) }
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

}