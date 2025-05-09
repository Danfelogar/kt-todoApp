package app.vercel.danfelogarporfolios.kt_todoapp.addtasks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface TaskDao {

    @Query("SELECT * from TaskEntity")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun addTask(item: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)
}