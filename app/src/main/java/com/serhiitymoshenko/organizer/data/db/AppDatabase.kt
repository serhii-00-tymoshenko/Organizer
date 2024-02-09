package com.serhiitymoshenko.organizer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.serhiitymoshenko.organizer.data.db.converters.BitmapConverter
import com.serhiitymoshenko.organizer.data.db.daos.ContactsDao
import com.serhiitymoshenko.organizer.data.db.daos.TasksDao
import com.serhiitymoshenko.organizer.data.db.entities.ContactEntity
import com.serhiitymoshenko.organizer.data.db.entities.TaskEntity

@TypeConverters(BitmapConverter::class)
@Database(entities = [TaskEntity::class, ContactEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    abstract fun contactsDao(): ContactsDao
}