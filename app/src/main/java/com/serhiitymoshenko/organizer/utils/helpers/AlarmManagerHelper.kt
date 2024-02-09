package com.serhiitymoshenko.organizer.utils.helpers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.serhiitymoshenko.organizer.data.models.task.Task
import com.serhiitymoshenko.organizer.data.models.task.TaskReminderStatus
import com.serhiitymoshenko.organizer.receivers.AlarmReceiver
import com.serhiitymoshenko.organizer.utils.TASK_ARGUMENT_KEY
import java.util.Calendar

class AlarmManagerHelper(private val context: Context) {

    private val alarmManager = context.getSystemService(AlarmManager::class.java) as AlarmManager
    private var alarmCalendar = Calendar.getInstance()

    fun schedule(tasks: List<Task>) {
        tasks.forEach { task ->
            val intent = Intent(context, AlarmReceiver::class.java).apply {
                putExtra(TASK_ARGUMENT_KEY, task)
            }

            val pendingIntent =
                PendingIntent.getBroadcast(
                    context,
                    task.id,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )

            setupAlarmCalendar(task)

            when (task.reminderStatus) {
                TaskReminderStatus.ONE_TIME -> {
                    alarmManager.setAndAllowWhileIdle(
                        AlarmManager.RTC,
                        alarmCalendar.timeInMillis,
                        pendingIntent
                    )
                }

                TaskReminderStatus.EVERY_DAY -> {
                    alarmManager.setRepeating(
                        AlarmManager.RTC,
                        alarmCalendar.timeInMillis,
                        24 * 60 * 60 * 1000,
                        pendingIntent
                    )
                }

                else -> {}
            }
        }
    }

    private fun setupAlarmCalendar(task: Task) {
        alarmCalendar.timeInMillis = System.currentTimeMillis()
        alarmCalendar.set(Calendar.HOUR_OF_DAY, task.reminderHour ?: 0)
        alarmCalendar.set(Calendar.MINUTE, task.reminderMinute ?: 0)
    }

    fun cancel() {
        alarmManager.cancel { }
    }
}