package org.ceenjs.ceenjs.ui.main.drawer

import android.content.Context

abstract class DrawerMenuItemCustomHelper internal constructor(private val mContext: Context) : DrawerMenuItemHelper {

    override val context: Context
        get() = mContext

    override fun active() {
        if (!isActive) toggle()
    }

}