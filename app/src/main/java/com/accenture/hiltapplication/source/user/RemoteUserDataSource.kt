package com.accenture.hiltapplication.source.user

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import com.accenture.hiltapplication.database.entity.UserEntity
import com.accenture.hiltapplication.network.NetworkService
import com.accenture.hiltapplication.source.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteUserDataSource internal constructor(
    private val networkService: NetworkService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserDataSource {

    private val observableUsers = MutableLiveData<DataResult<List<UserEntity>>>()

    override fun observeUsers(): LiveData<DataResult<List<UserEntity>>> {
        return observableUsers
    }

    override suspend fun getUsers(): DataResult<List<UserEntity>> = withContext(ioDispatcher) {
        return@withContext try {
            DataResult.Success(networkService.getUsers())
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    override suspend fun refreshUsers() {
        observableUsers.value = getUsers()
    }

    override suspend fun saveUser(user: UserEntity) {
        // NO-OP
    }

    override suspend fun deleteAllUsers() {
        // NO-OP
    }

    override suspend fun observeItemCount(): LiveData<Int> =
        observableUsers.distinctUntilChanged().switchMap { result ->
            val data = MutableLiveData(0)

            if (result is DataResult.Success) {
                data.value = result.data.size
            }

            data
        }
}