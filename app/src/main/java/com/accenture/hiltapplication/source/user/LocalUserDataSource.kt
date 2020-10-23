package com.accenture.hiltapplication.source.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.accenture.hiltapplication.database.dao.UserDao
import com.accenture.hiltapplication.database.entity.UserEntity
import com.accenture.hiltapplication.source.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalUserDataSource internal constructor(
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserDataSource {

    override fun observeUsers(): LiveData<DataResult<List<UserEntity>>> {
        return userDao.observeUsers().map { list ->
            DataResult.Success(list)
        }
    }

    override suspend fun getUsers(): DataResult<List<UserEntity>> = withContext(ioDispatcher) {
        return@withContext try {
            DataResult.Success(userDao.getUsers())
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    override suspend fun refreshUsers() {
        // NO-OP
    }

    override suspend fun saveUser(user: UserEntity) = withContext(ioDispatcher) {
        userDao.insert(user)
    }

    override suspend fun deleteAllUsers() = withContext(ioDispatcher) {
        userDao.deleteAll()
    }

    override suspend fun observeItemCount(): LiveData<Int> = userDao.observeItemCount()

}