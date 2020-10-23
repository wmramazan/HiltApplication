package com.accenture.hiltapplication.source.user

import androidx.lifecycle.LiveData
import com.accenture.hiltapplication.database.entity.UserEntity
import com.accenture.hiltapplication.source.DataResult

interface UserDataSource {
    fun observeUsers(): LiveData<DataResult<List<UserEntity>>>
    suspend fun getUsers(): DataResult<List<UserEntity>>
    suspend fun refreshUsers()
    suspend fun saveUser(user: UserEntity)
    suspend fun deleteAllUsers()
    suspend fun observeItemCount(): LiveData<Int>
}