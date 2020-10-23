package com.accenture.hiltapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.accenture.hiltapplication.database.dao.UserDao
import com.accenture.hiltapplication.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class
    ], version = 1, exportSchema = false
)
abstract class HiltDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}