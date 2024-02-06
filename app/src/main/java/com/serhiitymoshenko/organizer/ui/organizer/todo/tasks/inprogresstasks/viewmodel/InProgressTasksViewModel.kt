package com.serhiitymoshenko.organizer.ui.organizer.todo.tasks.inprogresstasks.viewmodel

import androidx.lifecycle.ViewModel
import com.serhiitymoshenko.organizer.data.entities.task.TaskStatus
import com.serhiitymoshenko.organizer.ui.organizer.todo.repositories.TasksRepository

class InProgressTasksViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    fun getInProgressTasks() = tasksRepository.getTasks(TaskStatus.IN_PROGRESS)
}