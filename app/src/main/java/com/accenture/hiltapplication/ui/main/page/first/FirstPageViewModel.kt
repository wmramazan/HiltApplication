package com.accenture.hiltapplication.ui.main.page.first

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.accenture.hiltapplication.common.BaseViewModel
import com.accenture.hiltapplication.database.entity.UserEntity
import com.accenture.hiltapplication.repository.user.Repository
import com.accenture.hiltapplication.source.DataResult
import kotlinx.coroutines.launch

class FirstPageViewModel @ViewModelInject constructor(
    private val userRepository: Repository<UserEntity>
) : BaseViewModel() {

    private val _forceUpdate = MutableLiveData(false)

    private val _users: LiveData<List<UserEntity>> = _forceUpdate.switchMap { forceUpdate ->
        showLoading()

        if (forceUpdate) {
            viewModelScope.launch {
                userRepository.refreshAll()
            }
        }
        userRepository.observeAll().distinctUntilChanged().switchMap { verifyResult(it) }
    }

    val users: LiveData<List<UserEntity>>
        get() = _users

    val loading = ObservableBoolean(true)
    val error = ObservableBoolean(false)
    val noUsers = ObservableBoolean(false)

    fun loadUsers(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    fun refresh() {
        _forceUpdate.value = true
    }

    private fun verifyResult(usersResult: DataResult<List<UserEntity>>): LiveData<List<UserEntity>> {
        val result = MutableLiveData<List<UserEntity>>()

        if (usersResult is DataResult.Success) {
            error.set(false)
            viewModelScope.launch {
                result.value = usersResult.data
                noUsers.set(usersResult.data.isEmpty())
            }
        } else {
            error.set(true)
            result.value = emptyList()
        }

        loading.set(false)

        return result
    }

    private fun showLoading() {
        loading.set(true)
        noUsers.set(false)
    }


}