package com.serhiitymoshenko.organizer.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "status")
    val status: TaskStatus,
    @ColumnInfo(name = "reminder_status")
    val reminderStatus: TaskReminderStatus,
    @ColumnInfo(name = "reminder_hour")
    val reminderHour: Int,
    @ColumnInfo(name = "reminder_minute")
    val reminderMinute: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
) : Parcelable