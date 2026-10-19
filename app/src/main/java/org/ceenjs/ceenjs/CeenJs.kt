package org.ceenjs.ceenjs

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.ceenjs.ceenjs.core.accessibility.AccessibilityTool
import org.ceenjs.ceenjs.core.accessibility.Capture
import org.ceenjs.ceenjs.core.accessibility.LayoutInspector.CaptureAvailableListener
import org.ceenjs.ceenjs.core.console.GlobalConsole
import org.ceenjs.ceenjs.execution.ScriptExecutionGlobalListener
import org.ceenjs.ceenjs.external.fileprovider.AppFileProvider
import org.ceenjs.ceenjs.ipc.LayoutInspectEvent
import org.ceenjs.ceenjs.ipc.LayoutInspectEventBus
import org.ceenjs.ceenjs.pluginclient.DevPluginService
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.AppUtils
import org.ceenjs.ceenjs.runtime.api.AppUtils.Companion.ActivityShortForm
import org.ceenjs.ceenjs.runtime.api.AppUtils.Companion.BroadcastShortForm
import org.ceenjs.ceenjs.ui.floating.FloatyWindowManger
import org.ceenjs.ceenjs.ui.floating.FullScreenFloatyWindow
import org.ceenjs.ceenjs.ui.floating.layoutinspector.LayoutBoundsFloatyWindow
import org.ceenjs.ceenjs.ui.floating.layoutinspector.LayoutHierarchyFloatyWindow
import org.ceenjs.ceenjs.util.RhinoUtils.isBackgroundThread
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import org.ceenjs.ceenjs.inrt.ceenjs.CeenJs as CeenJsInrt

/**
 * Created by Stardust on Apr 2, 2017.
 * Transformed by SuperMonster003 on Oct 10, 2022.
 * Modified by LZX284 (https://github.com/LZX284) as of Sep 30, 2023.
 * Modified by SuperMonster003 as of Jan 17, 2026.
 */
open class CeenJs(appContext: Application) : AbstractCeenJs(appContext) {

    internal val devPluginService by lazy {
        DevPluginService(application)
    }

    // @Thank to Zen2H
    // Use bounded queue to prevent log flooding from blocking the whole channel.
    // zh-CN: 使用有界队列避免日志洪泛导致整个通道被阻塞.
    private val mPrintExecutor = ThreadPoolExecutor(
        1,
        1,
        0L,
        TimeUnit.MILLISECONDS,
        ArrayBlockingQueue(2048),
        ThreadPoolExecutor.DiscardOldestPolicy(),
    )

    private val mA11yTool = AccessibilityTool(appContext)

    private val mJob = Job()
    private val mScope = CoroutineScope(Dispatchers.Main.immediate + mJob)

    init {
        scriptEngineService.registerGlobalScriptExecutionListener(ScriptExecutionGlobalListener())

        // @Archived by SuperMonster003 on Sep 27, 2025.
        //  ! LocalBroadcastManager is deprecated.
        //  ! zh-CN: LocalBroadcastManager 已被弃用.
        //  # LocalBroadcastManager.getInstance(appContext).registerReceiver(object : BroadcastReceiver() {
        //  #     override fun onReceive(context: Context, intent: Intent) {
        //  #         ... ...
        //  #         val action = intent.action ?: return
        //  #         when {
        //  #             action.equals(LayoutBoundsFloatyWindow::class.java.name, true) -> { ... ... }
        //  #             action.equals(LayoutHierarchyFloatyWindow::class.java.name, true) -> { ... ... }
        //  #         }
        //  #         ... ...
        //  #     }
        //  # }, IntentFilter().apply {
        //  #     addAction(LayoutBoundsFloatyWindow::class.java.name)
        //  #     addAction(LayoutHierarchyFloatyWindow::class.java.name)
        //  # })

        LayoutInspectEventBus.events.onEach { event ->
            try {
                when (event) {
                    LayoutInspectEvent.ShowLayoutBounds -> {
                        mA11yTool.ensureService()
                        capture(object : LayoutInspectFloatyWindow {
                            override fun create(capture: Capture) = LayoutBoundsFloatyWindow(capture, appContext, true)
                        })
                    }
                    LayoutInspectEvent.ShowLayoutHierarchy -> {
                        mA11yTool.ensureService()
                        capture(object : LayoutInspectFloatyWindow {
                            override fun create(capture: Capture) = LayoutHierarchyFloatyWindow(capture, appContext, true)
                        })
                    }
                }
            } catch (e: Exception) {
                if (isBackgroundThread()) {
                    throw e
                }
            }
        }.launchIn(mScope)
    }

    private interface LayoutInspectFloatyWindow {
        fun create(capture: Capture): FullScreenFloatyWindow
    }

    private fun capture(window: LayoutInspectFloatyWindow) {
        val inspector = layoutInspector
        val listener: CaptureAvailableListener = object : CaptureAvailableListener {
            override fun onCaptureAvailable(capture: Capture, context: Context) {
                inspector.removeCaptureAvailableListener(this)
                uiHandler.post { FloatyWindowManger.addWindow(context, window.create(capture)) }
            }
        }
        inspector.addCaptureAvailableListener(listener)
        if (!inspector.captureCurrentWindow()) {
            inspector.removeCaptureAvailableListener(listener)
        }
    }

    override fun createAppUtils(context: Context) = AppUtils(context, AppFileProvider.AUTHORITY)

    override fun createGlobalConsole(): GlobalConsole {
        return object : GlobalConsole(uiHandler) {
            override fun println(level: Int, charSequence: CharSequence): String {
                return super.println(level, charSequence).also {
                    // FIXME by SuperMonster003 as of Feb 2, 2022.
                    //  ! When running in "ui" thread (ui.run() or ui.post()),
                    //  ! android.os.NetworkOnMainThreadException may happen.
                    //  ! Furthermore, dunno if a thread executor is a good idea.
                    //  ! 当在 "ui" 线程执行时 (如 ui.run() 或 ui.post()),
                    //  ! 可能会发生 android.os.NetworkOnMainThreadException 异常.
                    //  ! 而且, 我不确定使用线程执行器是否是个好主意.
                    mPrintExecutor.submit { devPluginService.print(it) }
                }
            }
        }
    }

    override fun createRuntime(): ScriptRuntime = super.createRuntime().apply {

        /* Activities. */

        listOf(
            ActivityShortForm.SETTINGS, ActivityShortForm.PREFERENCES, ActivityShortForm.PREF,
            ActivityShortForm.DOCUMENTATION, ActivityShortForm.DOC, ActivityShortForm.DOCS,
            ActivityShortForm.HOMEPAGE, ActivityShortForm.HOME,
            ActivityShortForm.CONSOLE, ActivityShortForm.LOG,
            ActivityShortForm.ABOUT,
            ActivityShortForm.BUILD,
        ).forEach { putProperty(it.fullName, it.classType) }

        /* Broadcasts. */

        listOf(
            BroadcastShortForm.INSPECT_LAYOUT_BOUNDS, BroadcastShortForm.LAYOUT_BOUNDS, BroadcastShortForm.BOUNDS,
            BroadcastShortForm.INSPECT_LAYOUT_HIERARCHY, BroadcastShortForm.LAYOUT_HIERARCHY, BroadcastShortForm.HIERARCHY,
        ).forEach { putProperty(it.fullName, it.className) }
    }

    fun clear() {
        mJob.cancel()
    }

    companion object {
        private var isInitialized = false
        private val TAG = CeenJs::class.java.simpleName

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var instance: CeenJs
            private set

        @Synchronized
        @JvmStatic
        fun initInstance(application: Application) {
            Log.d(TAG, "CeenJs isInitialized: $isInitialized")
            if (!isInitialized) {
                instance = when (isInrt) {
                    true -> CeenJsInrt(application)
                    else -> CeenJs(application)
                }
                isInitialized = true
            }
        }
    }

}