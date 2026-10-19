package org.ceenjs.ceenjs.inrt.theme.preference

import android.content.Context
import android.util.AttributeSet
import org.ceenjs.ceenjs.service.ForegroundService
import org.ceenjs.ceenjs.theme.preference.ThemeColorServiceSwitchPreference

class ForegroundServiceSwitchPreference : ThemeColorServiceSwitchPreference {

    val svc by lazy { ForegroundService(context) }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun isRunning() = svc.isRunning

    override fun start() = svc.start()

    override fun stop() = svc.stop()

}