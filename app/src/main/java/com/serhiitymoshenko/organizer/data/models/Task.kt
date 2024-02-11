package com.serhiitymoshenko.organizer.data.models

import android.os.Parcelable
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
