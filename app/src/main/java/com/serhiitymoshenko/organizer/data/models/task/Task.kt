package com.serhiitymoshenko.organizer.data.models.task

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Int,
    val title: String,
    val status: TaskStatus,
    val reminderStatus: TaskReminderStatus,
    val reminderHour: Int?,
    val reminderMinute: Int?
) : Parcelable
