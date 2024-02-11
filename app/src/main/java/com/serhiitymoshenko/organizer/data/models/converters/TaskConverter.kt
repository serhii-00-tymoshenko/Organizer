package com.serhiitymoshenko.organizer.data.models.converters

import com.serhiitymoshenko.organizer.data.db.entities.TaskEntity
import com.serhiitymoshenko.organizer.data.models.Task

fun TaskEntity.fromEntityToTask() =
    Task(
        this.id ?: 0,
        this.title,
        this.status,
        this.reminderStatus,
        this.reminderHour,
        this.reminderMinute
    )

fun List<TaskEntity>.fromEntitiesToTasks() =
    this.map { it.fromEntityToTask() }


fun Task.toTaskEntity() =
    TaskEntity(
        null ?: this.id,
        this.title,
        this.status,
        this.reminderStatus,
        this.reminderHour,
        this.reminderMinute
    )