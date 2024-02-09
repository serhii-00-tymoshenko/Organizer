package com.serhiitymoshenko.organizer.ui.home.todo.edittask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiitymoshenko.organizer.data.db.entities.TaskEntity
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class EditTaskViewModel(private val repository: TasksRepository) : ViewModel() {

    fun updateTask(taskEntity: TaskEntity) = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        repository.updateTask(taskEntity)
    }
}