package com.accenture.hiltapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.accenture.hiltapplication.database.entity.UserEntity

@Dao
interface UserDao {
    @Query("select * from user")
    fun observeUsers(): LiveData<List<UserEntity>>

    @Query("select * from user")
    fun getUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: UserEntity)

    @Query("delete from user")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM user")
    fun observeItemCount(): LiveData<Int>
}