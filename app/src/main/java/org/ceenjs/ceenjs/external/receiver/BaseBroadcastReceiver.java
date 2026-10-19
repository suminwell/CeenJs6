package org.ceenjs.ceenjs.external.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.ceenjs.ceenjs.CeenJs;
import org.ceenjs.ceenjs.execution.ExecutionConfig;
import org.ceenjs.ceenjs.model.script.ScriptFile;
import org.ceenjs.ceenjs.timing.IntentTask;
import org.ceenjs.ceenjs.timing.TimedTaskManager;
import org.ceenjs.ceenjs.util.ViewUtils;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseBroadcastReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = "BaseBroadcastReceiver";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "onReceive: intent = " + intent + ", this = " + this);
        try {
            TimedTaskManager.getIntentTaskOfAction(intent.getAction())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(intentTask -> runTask(context, intent, intentTask), Throwable::printStackTrace);
        } catch (Exception e) {
            ViewUtils.showToast(context, e.getMessage(), true);
        }
    }

    public static void runTask(Context context, Intent intent, IntentTask task) {
        Log.d(LOG_TAG, "runTask: action = " + intent.getAction() + ", script = " + task.getScriptPath());
        ScriptFile file = new ScriptFile(task.getScriptPath());
        ExecutionConfig config = new ExecutionConfig();
        config.setArgument("intent", intent.clone());
        config.setWorkingDirectory(Objects.requireNonNull(file.getParent()));
        try {
            CeenJs.getInstance().getScriptEngineService().execute(file.toSource(), config);
        } catch (Exception e) {
            e.printStackTrace();
            ViewUtils.showToast(context, e.getMessage(), true);
        }
    }

}
