package com.serhiitymoshenko.organizer.ui.home.todo.children.tasks.inprogresstasks.viewmodel

import androidx.lifecycle.ViewModel
import com.serhiitymoshenko.organizer.data.models.converters.fromEntitiesToTasks
import com.serhiitymoshenko.organizer.data.models.task.TaskStatus
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import kotlinx.coroutines.flow.map

class InProgressTasksViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    fun getInProgressTasks() = tasksRepository.getTasks(TaskStatus.IN_PROGRESS)
        .map { tasks -> tasks.fromEntitiesToTasks() }
}