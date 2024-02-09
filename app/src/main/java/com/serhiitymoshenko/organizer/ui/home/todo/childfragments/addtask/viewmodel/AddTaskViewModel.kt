package com.serhiitymoshenko.organizer.ui.home.todo.childfragments.addtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiitymoshenko.organizer.data.db.entities.TaskEntity
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AddTaskViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    fun insertTask(taskEntity: TaskEntity) = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        tasksRepository.insertTask(taskEntity)
    }
}