package com.serhiitymoshenko.organizer.ui.organizer.todo.edittask.viewmodel

import androidx.lifecycle.ViewModel
import com.serhiitymoshenko.organizer.ui.organizer.todo.repositories.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class EditTaskViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    private val coroutineContext = Dispatchers.IO + SupervisorJob()


}