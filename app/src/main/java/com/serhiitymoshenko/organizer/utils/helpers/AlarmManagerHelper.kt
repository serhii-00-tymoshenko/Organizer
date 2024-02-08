package com.serhiitymoshenko.organizer.utils.helpers

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.serhiitymoshenko.organizer.data.models.Task
import com.serhiitymoshenko.organizer.data.models.TaskReminderStatus
import com.serhiitymoshenko.organizer.receivers.AlarmReceiver
import com.serhiitymoshenko.organizer.utils.ALARM_MANAGER_REQUEST_CODE
import com.serhiitymoshenko.organizer.utils.TEXT_ARGUMENT_NAME
import java.util.Calendar
import kotlin.math.log

class AlarmManagerHelper(private val context: Context) {

    private val alarmManager = context.getSystemService(AlarmManager::class.java) as AlarmManager
    private var alarmCalendar = Calendar.getInstance()

    fun schedule(tasks: List<Task>) {
        tasks.forEach { task ->
            val intent = Intent(context, AlarmReceiver::class.java).apply {
                putExtra(TEXT_ARGUMENT_NAME, task.title)
            }

            val pendingIntent =
                PendingIntent.getBroadcast(
                    context,
                    ALARM_MANAGER_REQUEST_CODE,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )

            alarmCalendar.timeInMillis = System.currentTimeMillis()
            alarmCalendar.set(Calendar.HOUR_OF_DAY, task.reminderHour)
            alarmCalendar.set(Calendar.MINUTE, task.reminderMinute)

            when (task.reminderStatus) {
                TaskReminderStatus.ONE_TIME -> {
                    alarmManager.setAndAllowWhileIdle(AlarmManager.RTC, alarmCalendar.timeInMillis, pendingIntent)
                }

                TaskReminderStatus.EVERY_DAY -> {
                    alarmManager.setRepeating(AlarmManager.RTC, alarmCalendar.timeInMillis, 24 * 60 * 60 * 1000, pendingIntent)
                }

                else -> {}
            }
        }
    }

    fun cancel() {
        alarmManager.cancel { }
    }
}