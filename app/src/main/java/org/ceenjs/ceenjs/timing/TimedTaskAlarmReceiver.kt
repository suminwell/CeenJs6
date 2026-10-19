package org.ceenjs.ceenjs.timing

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import org.ceenjs.ceenjs.external.ScriptIntents
import org.ceenjs.ceenjs.timing.AlarmTimedTaskScheduler.ACTION_RUN_ALARM_TIMED_TASK
import org.ceenjs.ceenjs.timing.TaskReceiver.EXTRA_TASK_ID

class TimedTaskAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != ACTION_RUN_ALARM_TIMED_TASK) return
        val id = intent.getLongExtra(EXTRA_TASK_ID, -1L)
        if (id <= 0) return
        val task = TimedTaskManager.getTimedTask(id)
        Log.d("TimedTaskAlarmReceiver", "onReceive: id=$id, task=$task")
        if (task == null) return
        val runIntent = task.createIntent()
        ScriptIntents.handleIntent(context, runIntent)
        TimedTaskManager.notifyTaskFinished(task.id)
    }

}
