package com.serhiitymoshenko.organizer.ui.organizer.todo.repositories

import com.serhiitymoshenko.organizer.data.db.daos.TasksDao
import com.serhiitymoshenko.organizer.data.entities.task.TaskStatus

class TasksRepository(private val tasksDao: TasksDao) {
    fun getTasks(status: TaskStatus) = tasksDao.getTasks(status)

    fun getSearchedTasks(query: String) = tasksDao.getSearchedTasks(query)
}