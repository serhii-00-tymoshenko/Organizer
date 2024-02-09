package com.serhiitymoshenko.organizer.ui.home.todo.children.tasks.donetasks.viewmodel

import androidx.lifecycle.ViewModel
import com.serhiitymoshenko.organizer.data.models.converters.fromEntitiesToTasks
import com.serhiitymoshenko.organizer.data.models.task.TaskStatus
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import kotlinx.coroutines.flow.map

class DoneTasksViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    fun getDoneTasks() = tasksRepository.getTasks(TaskStatus.DONE)
        .map { tasks -> tasks.fromEntitiesToTasks() }
}