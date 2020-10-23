package com.accenture.hiltapplication.ui.main.page.second

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.accenture.hiltapplication.common.BaseViewModel
import com.accenture.hiltapplication.di.DataSourceModule
import com.accenture.hiltapplication.source.user.UserDataSource
import kotlinx.coroutines.launch

class SecondPageViewModel @ViewModelInject constructor(
    @DataSourceModule.UserDataSourceLocal private val dataSource: UserDataSource
) : BaseViewModel() {

    var itemCount: LiveData<Int>? = null

    init {
        updateItemCount()
    }

    fun onButtonClicked() {
        ioScope.launch {
            dataSource.deleteAllUsers()
        }
    }

    private fun updateItemCount() {
        ioScope.launch {
            itemCount = dataSource.observeItemCount()
        }
    }

}