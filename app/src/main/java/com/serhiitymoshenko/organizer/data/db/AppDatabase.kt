package com.serhiitymoshenko.organizer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.serhiitymoshenko.organizer.data.db.daos.TasksDao
import com.serhiitymoshenko.organizer.data.entities.task.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao
}