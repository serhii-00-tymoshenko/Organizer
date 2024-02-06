package com.serhiitymoshenko.organizer.data.db.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.serhiitymoshenko.organizer.data.db.daos.TasksDao
import com.serhiitymoshenko.organizer.data.entities.task.Task
import com.serhiitymoshenko.organizer.data.entities.task.TaskReminderStatus
import com.serhiitymoshenko.organizer.data.entities.task.TaskStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class AppDatabaseCallback : RoomDatabase.Callback(), KoinComponent {

    private val coroutineContext: CoroutineContext by inject<CoroutineContext>()
    private val tasksDao: TasksDao by inject<TasksDao>()

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        CoroutineScope(coroutineContext).launch {
            prepopulate()
        }
    }

    private fun prepopulate() {
        tasksDao.insertTask(
            Task(
                "Click me to edit or delete",
                TaskStatus.IN_PROGRESS,
                TaskReminderStatus.NONE,
                0
            )
        )
        tasksDao.insertTask(
            Task(
                "Hello",
                TaskStatus.IN_PROGRESS,
                TaskReminderStatus.NONE,
                0
            )
        )
        tasksDao.insertTask(
            Task(
                "Dmytro, ce rz ui",
                TaskStatus.IN_PROGRESS,
                TaskReminderStatus.NONE,
                0
            )
        )
    }
}