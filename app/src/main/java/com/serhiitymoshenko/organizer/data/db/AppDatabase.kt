package com.serhiitymoshenko.organizer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.serhiitymoshenko.organizer.data.db.converters.BitmapConverter
import com.serhiitymoshenko.organizer.data.db.daos.ContactsDao
import com.serhiitymoshenko.organizer.data.db.daos.TasksDao
import com.serhiitymoshenko.organizer.data.models.Contact
import com.serhiitymoshenko.organizer.data.models.Task

@TypeConverters(BitmapConverter::class)
@Database(entities = [Task::class, Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    abstract fun contactsDao(): ContactsDao
}