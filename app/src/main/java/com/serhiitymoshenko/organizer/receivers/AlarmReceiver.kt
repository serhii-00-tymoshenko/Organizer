package com.serhiitymoshenko.organizer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.serhiitymoshenko.organizer.data.models.converters.toTaskEntity
import com.serhiitymoshenko.organizer.data.models.task.Task
import com.serhiitymoshenko.organizer.data.models.task.TaskReminderStatus
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import com.serhiitymoshenko.organizer.utils.TASK_ARGUMENT_KEY
import com.serhiitymoshenko.organizer.utils.helpers.NotificationsHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class AlarmReceiver : BroadcastReceiver(), KoinComponent {

    private val tasksRepository by inject<TasksRepository>()
    private val coroutineScope by inject<CoroutineScope>()

    override fun onReceive(context: Context?, intent: Intent?) {
        val task = intent?.getParcelableExtra<Task>(TASK_ARGUMENT_KEY)

        context?.let {
            val notificationsHelper = NotificationsHelper(context, task?.id ?: 0)

            notificationsHelper.getNotification()

            task?.let {
                notificationsHelper.updateNotification(task)

                if (task.reminderStatus == TaskReminderStatus.ONE_TIME) {
                    coroutineScope.launch {
                        tasksRepository.updateTask(
                            task.copy(
                                reminderStatus = TaskReminderStatus.NONE,
                                reminderMinute = null,
                                reminderHour = null
                            ).toTaskEntity()
                        )
                    }
                }
            }
        }
    }
}