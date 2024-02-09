package com.serhiitymoshenko.organizer.data.db.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.serhiitymoshenko.organizer.data.db.daos.TasksDao
import com.serhiitymoshenko.organizer.data.db.entities.TaskEntity
import com.serhiitymoshenko.organizer.data.models.task.TaskReminderStatus
import com.serhiitymoshenko.organizer.data.models.task.TaskStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppDatabaseCallback : RoomDatabase.Callback(), KoinComponent {

    private val coroutineScope by inject<CoroutineScope>()
    private val tasksDao: TasksDao by inject<TasksDao>()

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        prepopulate()
    }

    private fun prepopulate() {
        coroutineScope.launch {
            tasksDao.insertTask(
                TaskEntity(
                    null,
                    "Click me to edit",
                    TaskStatus.IN_PROGRESS,
                    TaskReminderStatus.NONE,
                    null,
                    null
                )
            )
        }
    }
}