package org.ceenjs.ceenjs

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.benjaminwan.ocrlibrary.OcrEngine
import org.ceenjs.ceenjs.app.SimpleActivityLifecycleCallbacks
import org.ceenjs.ceenjs.core.accessibility.AccessibilityBridgeImpl
import org.ceenjs.ceenjs.core.accessibility.AccessibilityNotificationObserver
import org.ceenjs.ceenjs.core.accessibility.AccessibilityService
import org.ceenjs.ceenjs.core.accessibility.LayoutInspector
import org.ceenjs.ceenjs.core.activity.ActivityInfoProvider
import org.ceenjs.ceenjs.core.console.GlobalConsole
import org.ceenjs.ceenjs.core.pref.Pref.registerOnSharedPreferenceChangeListener
import org.ceenjs.ceenjs.core.record.accessibility.AccessibilityActionRecorder
import org.ceenjs.ceenjs.engine.LoopBasedJavaScriptEngine
import org.ceenjs.ceenjs.engine.RootAutomatorEngine
import org.ceenjs.ceenjs.engine.ScriptEngineManager
import org.ceenjs.ceenjs.engine.ScriptEngineService
import org.ceenjs.ceenjs.engine.ScriptEngineServiceBuilder
import org.ceenjs.ceenjs.inrt.ceenjs.LoopBasedJavaScriptEngineWithDecryption
import org.ceenjs.ceenjs.rhino.InterruptibleAndroidContextFactory
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.AppUtils
import org.ceenjs.ceenjs.runtime.api.ScreenMetrics
import org.ceenjs.ceenjs.runtime.api.Shell
import org.ceenjs.ceenjs.runtime.api.WrappedShizuku
import org.ceenjs.ceenjs.script.AutoFileSource
import org.ceenjs.ceenjs.script.JavaScriptSource
import org.ceenjs.ceenjs.tool.UiHandler
import org.ceenjs.ceenjs.util.StringUtils
import org.ceenjs.ceenjs.util.ViewUtils
import org.ceenjs.ceenjs6.BuildConfig
import org.ceenjs.ceenjs6.R
import org.mozilla.javascript.ContextFactory
import java.io.File

/**
 * Created by Stardust on Nov 29, 2017.
 * Modified by SuperMonster003 as of Jun 10, 2022.
 * Transformed by SuperMonster003 on Oct 10, 2022.
 * Modified by LZX284 (https://github.com/LZX284) as of Sep 30, 2023.
 */
abstract class AbstractCeenJs protected constructor(val application: Application) {

    val applicationContext: Context = application.applicationContext
    val appUtils by lazy { createAppUtils(applicationContext) }
    val globalConsole by lazy { createGlobalConsole() }

    val rapidOcrEngine by lazy { OcrEngine(applicationContext) }

    val layoutInspector = LayoutInspector(applicationContext)
    val uiHandler = UiHandler(applicationContext)
    val infoProvider = ActivityInfoProvider(applicationContext)
    val scriptEngineService: ScriptEngineService = run {
        val scriptEngineManager = ScriptEngineManager(applicationContext)
        scriptEngineManager.registerEngine(JavaScriptSource.ENGINE) {
            val rt = createRuntime()
            when (isInrt) {
                true -> LoopBasedJavaScriptEngineWithDecryption(applicationContext)
                else -> LoopBasedJavaScriptEngine(applicationContext)
            }.also { it.runtime = rt }
        }
        initContextFactory()
        scriptEngineManager.registerEngine(AutoFileSource.ENGINE) { RootAutomatorEngine(applicationContext) }
        ScriptEngineServiceBuilder()
            .uiHandler(uiHandler)
            .globalConsole(globalConsole)
            .engineManger(scriptEngineManager)
            .build()
            .also { ScriptEngineService.setInstance(it) }
    }

    val notificationObserver = AccessibilityNotificationObserver(applicationContext)

    private val accessibilityActionRecorder = AccessibilityActionRecorder()

    init {
        addAccessibilityServiceDelegates()
        registerActivityLifecycleCallbacks()
        WrappedShizuku.onCreate()
    }

    private fun initContextFactory() {
        ContextFactory.initGlobal(InterruptibleAndroidContextFactory(File(applicationContext.cacheDir, "classes")))
    }

    protected open fun createRuntime(): ScriptRuntime = ScriptRuntime.Builder()
        .setConsole(globalConsole)
        .setUiHandler(uiHandler)
        .setAccessibilityBridge(createAccessibilityBridge())
        .setAppUtils(appUtils)
        .setShellSupplier { Shell(applicationContext, true) }
        .build()

    fun createAccessibilityBridge() = AccessibilityBridgeImpl(this)

    private fun addAccessibilityServiceDelegates() {
        AccessibilityService.addDelegate(100, infoProvider)
        AccessibilityService.addDelegate(200, notificationObserver)
        AccessibilityService.addDelegate(300, accessibilityActionRecorder)
    }

    private fun registerActivityLifecycleCallbacks() {
        application.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks() {

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                ScreenMetrics.init(activity)
                appUtils.setCurrentActivity(activity)
                registerOnSharedPreferenceChangeListener { _, key ->
                    if (key == StringUtils.key(R.string.key_keep_screen_on_when_in_foreground)) {
                        configKeepScreenOnWhenInForeground(activity)
                    }
                }
            }

            override fun onActivityPaused(activity: Activity) = appUtils.setCurrentActivity(null)

            override fun onActivityResumed(activity: Activity) {
                configKeepScreenOnWhenInForeground(activity)
                appUtils.setCurrentActivity(activity)
            }

            private fun configKeepScreenOnWhenInForeground(activity: Activity) {
                if (ViewUtils.isKeepScreenOnWhenInForegroundAllPages) {
                    activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                } else {
                    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }
            }
        })
    }

    open fun createAppUtils(context: Context) = AppUtils(context)

    open fun createGlobalConsole() = GlobalConsole(uiHandler)

    companion object {

        @JvmStatic
        @Suppress("KotlinConstantConditions")
        val isInrt get() = BuildConfig.isInrt

    }

}