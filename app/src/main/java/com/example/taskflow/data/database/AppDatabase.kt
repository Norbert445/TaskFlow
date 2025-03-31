package com.example.taskflow.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskflow.data.database.dao.TodoDao
import com.example.taskflow.domain.models.Todo

const val DATABASE_NAME = "APP_DATABASE"

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}