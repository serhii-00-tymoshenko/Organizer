package com.serhiitymoshenko.organizer.ui.home.todo.viewmodel

import androidx.lifecycle.ViewModel
import com.serhiitymoshenko.organizer.data.models.Task
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class TodoViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val searchedTasks: Flow<List<Task>> =  searchQuery.flatMapLatest { tasksRepository.getSearchedTasks(it) }
    fun getSearchedTasks() = searchedTasks
}