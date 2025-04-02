package com.example.taskflow.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskflow.domain.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query(
        """
        SELECT * FROM todo 
        ORDER BY 
        isDone ASC,
        CASE WHEN isDone = 0 THEN updatedAt END ASC,
        CASE WHEN isDone = 1 THEN updatedAt END DESC
    """)
    fun getTodos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}