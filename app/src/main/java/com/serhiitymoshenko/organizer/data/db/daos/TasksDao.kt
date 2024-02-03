package com.serhiitymoshenko.organizer.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.serhiitymoshenko.organizer.data.entities.task.Task
import com.serhiitymoshenko.organizer.data.entities.task.TaskStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {
    @Query("SELECT * FROM tasks WHERE status LIKE :taskStatus ORDER BY title")
    fun getTasks(taskStatus: TaskStatus): Flow<List<Task>>

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Insert
    fun insertTask(task: Task)
}