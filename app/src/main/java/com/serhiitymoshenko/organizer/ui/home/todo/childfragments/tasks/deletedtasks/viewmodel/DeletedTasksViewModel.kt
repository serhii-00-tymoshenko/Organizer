package com.serhiitymoshenko.organizer.ui.home.todo.childfragments.tasks.deletedtasks.viewmodel

import androidx.lifecycle.ViewModel
import com.serhiitymoshenko.organizer.data.models.converters.fromEntitiesToTasks
import com.serhiitymoshenko.organizer.data.models.task.TaskStatus
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import kotlinx.coroutines.flow.map

class DeletedTasksViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    fun getDeletedTasks() = tasksRepository.getTasks(TaskStatus.DELETED)
        .map { tasks -> tasks.fromEntitiesToTasks() }
}