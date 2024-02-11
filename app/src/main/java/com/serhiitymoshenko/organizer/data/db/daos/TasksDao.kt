package com.serhiitymoshenko.organizer.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.serhiitymoshenko.organizer.data.db.entities.TaskEntity
import com.serhiitymoshenko.organizer.data.models.TaskStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {
    @Query("SELECT * FROM tasks WHERE status LIKE :taskStatus ORDER BY title")
    fun getTasks(taskStatus: TaskStatus): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :query || '%'")
    fun getSearchedTasks(query: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE reminder_status LIKE 1 OR 2")
    fun getTasksWithReminder(): Flow<List<TaskEntity>>

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

    @Query("DELETE FROM tasks WHERE status LIKE :status")
    fun deleteAllTasks(status: TaskStatus)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)
}