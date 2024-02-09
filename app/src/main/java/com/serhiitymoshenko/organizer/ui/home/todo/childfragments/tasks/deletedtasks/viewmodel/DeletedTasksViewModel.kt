package com.serhiitymoshenko.organizer.ui.home.todo.childfragments.tasks.deletedtasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiitymoshenko.organizer.data.db.entities.TaskEntity
import com.serhiitymoshenko.organizer.data.models.converters.fromEntitiesToTasks
import com.serhiitymoshenko.organizer.data.models.task.TaskStatus
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DeletedTasksViewModel(private val repository: TasksRepository) : ViewModel() {

    fun getDeletedTasks() = repository.getTasks(TaskStatus.DELETED)
        .map { tasks -> tasks.fromEntitiesToTasks() }

    fun deleteTask(taskEntity: TaskEntity) = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        repository.deleteTask(taskEntity)
    }
}