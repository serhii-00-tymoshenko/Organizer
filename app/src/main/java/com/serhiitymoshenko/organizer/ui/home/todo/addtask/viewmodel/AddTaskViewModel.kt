package com.serhiitymoshenko.organizer.ui.home.todo.addtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiitymoshenko.organizer.data.models.Task
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AddTaskViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    fun insertTask(task: Task) = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        tasksRepository.insertTask(task)
    }
}