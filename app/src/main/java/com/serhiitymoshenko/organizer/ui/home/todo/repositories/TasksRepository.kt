package com.serhiitymoshenko.organizer.ui.home.todo.repositories

import com.serhiitymoshenko.organizer.data.db.daos.TasksDao
import com.serhiitymoshenko.organizer.data.db.entities.TaskEntity
import com.serhiitymoshenko.organizer.data.models.task.TaskStatus

class TasksRepository(private val tasksDao: TasksDao) {

    fun getTasks(status: TaskStatus) = tasksDao.getTasks(status)

    fun getSearchedTasks(query: String) = tasksDao.getSearchedTasks(query)

    fun getTasksWithReminder() = tasksDao.getTasksWithReminder()

    fun insertTask(taskEntity: TaskEntity) = tasksDao.insertTask(taskEntity)

    fun updateTask(taskEntity: TaskEntity) = tasksDao.updateTask(taskEntity)
}