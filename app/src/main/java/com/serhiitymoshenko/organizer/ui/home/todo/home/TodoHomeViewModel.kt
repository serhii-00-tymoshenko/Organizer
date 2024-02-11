package com.serhiitymoshenko.organizer.ui.home.todo.home

import androidx.lifecycle.ViewModel
import com.serhiitymoshenko.organizer.data.models.converters.fromEntitiesToTasks
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class TodoHomeViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val searchedTasks =  searchQuery.flatMapLatest { tasksRepository.getSearchedTasks(it).map { entities -> entities.fromEntitiesToTasks() } }
    fun getSearchedTasks() = searchedTasks

    fun getTasksWithReminder() = tasksRepository.getTasksWithReminder().map { entities -> entities.fromEntitiesToTasks() }
}