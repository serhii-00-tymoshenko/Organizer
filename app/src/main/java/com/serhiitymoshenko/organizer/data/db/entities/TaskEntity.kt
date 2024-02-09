package com.serhiitymoshenko.organizer.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serhiitymoshenko.organizer.data.models.task.TaskReminderStatus
import com.serhiitymoshenko.organizer.data.models.task.TaskStatus
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "status")
    val status: TaskStatus,
    @ColumnInfo(name = "reminder_status")
    val reminderStatus: TaskReminderStatus,
    @ColumnInfo(name = "reminder_hour")
    val reminderHour: Int?,
    @ColumnInfo(name = "reminder_minute")
    val reminderMinute: Int?,
)