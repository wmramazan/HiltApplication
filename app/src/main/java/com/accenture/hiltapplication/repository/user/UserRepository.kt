package com.accenture.hiltapplication.repository.user

import androidx.lifecycle.LiveData
import com.accenture.hiltapplication.database.entity.UserEntity
import com.accenture.hiltapplication.source.DataResult
import com.accenture.hiltapplication.source.user.UserDataSource

class UserRepository(
    private val remoteUserDataSource: UserDataSource,
    private val localUserDataSource: UserDataSource
) : Repository<UserEntity> {

    override fun observeAll(): LiveData<DataResult<List<UserEntity>>> {
        return localUserDataSource.observeUsers()
    }

    override suspend fun getAll(forceUpdate: Boolean): DataResult<List<UserEntity>> {
        if (forceUpdate) {
            try {
                updateUsersFromRemoteDataSource()
            } catch (ex: Exception) {
                return DataResult.Error(ex)
            }
        }
        return localUserDataSource.getUsers()
    }

    override suspend fun refreshAll() {
        updateUsersFromRemoteDataSource()
    }

    private suspend fun updateUsersFromRemoteDataSource() {
        val remoteUsers = remoteUserDataSource.getUsers()

        if (remoteUsers is DataResult.Success) {
            localUserDataSource.deleteAllUsers()
            remoteUsers.data.forEach { user ->
                localUserDataSource.saveUser(user)
            }
        } else if (remoteUsers is DataResult.Error) {
            throw remoteUsers.exception
        }
    }
}