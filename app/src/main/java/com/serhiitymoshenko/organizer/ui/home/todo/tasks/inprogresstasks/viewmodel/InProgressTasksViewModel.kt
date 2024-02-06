package com.serhiitymoshenko.organizer.ui.home.todo.tasks.inprogresstasks.viewmodel

import androidx.lifecycle.ViewModel
import com.serhiitymoshenko.organizer.data.models.TaskStatus
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository

class InProgressTasksViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    fun getInProgressTasks() = tasksRepository.getTasks(TaskStatus.IN_PROGRESS)
}