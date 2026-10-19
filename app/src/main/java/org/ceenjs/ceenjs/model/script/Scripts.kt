package org.ceenjs.ceenjs.model.script

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.ceenjs.ceenjs.CeenJs
import org.ceenjs.ceenjs.app.GlobalAppContext
import org.ceenjs.ceenjs.execution.ExecutionConfig
import org.ceenjs.ceenjs.execution.ScriptExecution
import org.ceenjs.ceenjs.execution.SimpleScriptExecutionListener
import org.ceenjs.ceenjs.external.ScriptIntents
import org.ceenjs.ceenjs.external.fileprovider.AppFileProvider
import org.ceenjs.ceenjs.external.shortcut.Shortcut
import org.ceenjs.ceenjs.external.shortcut.ShortcutActivity
import org.ceenjs.ceenjs.runtime.api.Mime
import org.ceenjs.ceenjs.runtime.exception.ScriptInterruptedException
import org.ceenjs.ceenjs.script.ScriptSource
import org.ceenjs.ceenjs.ui.edit.EditActivity
import org.ceenjs.ceenjs.util.FileUtils
import org.ceenjs.ceenjs.util.IntentUtils
import org.ceenjs.ceenjs.util.IntentUtils.ToastExceptionHolder
import org.ceenjs.ceenjs.util.ViewUtils
import org.ceenjs.ceenjs.util.WorkingDirectoryUtils
import org.ceenjs.ceenjs6.R
import org.mozilla.javascript.RhinoException
import java.io.File
import java.io.FileFilter

/**
 * Created by Stardust on May 3, 2017.
 */
object Scripts {

    private val globalAppContext: Context = GlobalAppContext.get()

    const val ACTION_ON_EXECUTION_FINISHED = "ACTION_ON_EXECUTION_FINISHED"
    const val EXTRA_EXCEPTION_MESSAGE = "message"
    const val EXTRA_EXCEPTION_LINE_NUMBER = "lineNumber"
    const val EXTRA_EXCEPTION_COLUMN_NUMBER = "columnNumber"

    @JvmField
    val FILE_FILTER = FileFilter { file ->
        file.isDirectory ||
        file.name.endsWith(FileUtils.TYPE.JAVASCRIPT.extension, true) ||
        file.name.endsWith(FileUtils.TYPE.AUTO.extension, true)
    }

    private val BROADCAST_SENDER_SCRIPT_EXECUTION_LISTENER = object : SimpleScriptExecutionListener() {

        override fun onSuccess(execution: ScriptExecution, result: Any?) {
            globalAppContext.sendBroadcast(Intent(ACTION_ON_EXECUTION_FINISHED))
        }

        override fun onException(execution: ScriptExecution, e: Throwable) {
            val rhinoException = getRhinoException(e)
            var line = -1
            var col = 0
            if (rhinoException != null) {
                line = rhinoException.lineNumber()
                col = rhinoException.columnNumber()
            }
            if (ScriptInterruptedException.causedByInterrupt(e)) {
                globalAppContext.sendBroadcast(
                    Intent(ACTION_ON_EXECUTION_FINISHED)
                        .putExtra(EXTRA_EXCEPTION_LINE_NUMBER, line)
                        .putExtra(EXTRA_EXCEPTION_COLUMN_NUMBER, col)
                )
            } else {
                globalAppContext.sendBroadcast(
                    Intent(ACTION_ON_EXECUTION_FINISHED)
                        .putExtra(EXTRA_EXCEPTION_MESSAGE, e.message)
                        .putExtra(EXTRA_EXCEPTION_LINE_NUMBER, line)
                        .putExtra(EXTRA_EXCEPTION_COLUMN_NUMBER, col)
                )
            }
        }

    }

    @JvmStatic
    fun openByOtherApps(uri: Uri) {
        IntentUtils.viewFile(
            globalAppContext,
            uri,
            Mime.TEXT_PLAIN,
            AppFileProvider.AUTHORITY,
            ToastExceptionHolder(globalAppContext),
        )
    }

    @JvmStatic
    fun openByOtherApps(file: File) {
        openByOtherApps(Uri.fromFile(file))
    }

    fun createShortcut(context: Context, scriptFile: ScriptFile) {
        Shortcut(context).name(scriptFile.simplifiedName)
            .targetClass(ShortcutActivity::class.java)
            .iconRes(R.drawable.ic_node_js_black)
            .extras(Intent().putExtra(ScriptIntents.EXTRA_KEY_PATH, scriptFile.path))
            .send()
    }

    @JvmStatic
    fun edit(context: Context, file: ScriptFile) {
        EditActivity.editFile(context, file.simplifiedName, file.path, true)
    }

    @JvmStatic
    fun edit(context: Context, path: String) {
        edit(context, ScriptFile(path))
    }

    @JvmStatic
    fun run(context: Context, file: ScriptFile): ScriptExecution? = try {
        file.parent?.let {
            CeenJs.instance.scriptEngineService.execute(file.toSource(), ExecutionConfig(workingDirectory = it))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        ViewUtils.showToast(context, e.message, true)
        null
    }

    @JvmStatic
    fun run(context: Context, source: ScriptSource): ScriptExecution? = try {
        CeenJs.instance.scriptEngineService.execute(source, ExecutionConfig(WorkingDirectoryUtils.path))
    } catch (e: Exception) {
        e.printStackTrace()
        ViewUtils.showToast(context, e.message, true)
        null
    }

    @JvmStatic
    fun runWithBroadcastSender(file: File, workingDirectory: String?, overriddenFullPath: String?): ScriptExecution? {
        return CeenJs.instance.scriptEngineService.execute(
            ScriptFile(file).toSource().apply { this.overriddenFullPath = overriddenFullPath },
            BROADCAST_SENDER_SCRIPT_EXECUTION_LISTENER,
            ExecutionConfig(workingDirectory ?: file.parent ?: "")
        )
    }

    @JvmStatic
    fun runRepeatedly(scriptFile: ScriptFile, loopTimes: Int, delay: Long, interval: Long) {
        val source = scriptFile.toSource()
        val directoryPath = scriptFile.parent
        if (directoryPath != null) {
            CeenJs.instance.scriptEngineService.execute(
                source, ExecutionConfig(
                    workingDirectory = directoryPath,
                    delay = delay, loopTimes = loopTimes, interval = interval
                )
            )
        }
    }

    fun getRhinoException(throwable: Throwable?): RhinoException? {
        var e = throwable
        while (e != null) {
            if (e is RhinoException) {
                return e
            }
            e = e.cause
        }
        return null
    }

    @JvmStatic
    fun send(context: Context, file: ScriptFile) {
        IntentUtils.sendFile(
            context,
            file.path,
            AppFileProvider.AUTHORITY,
            ToastExceptionHolder(context),
        )
    }

}
