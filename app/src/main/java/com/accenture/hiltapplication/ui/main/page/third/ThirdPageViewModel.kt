package com.accenture.hiltapplication.ui.main.page.third

import androidx.hilt.lifecycle.ViewModelInject
import com.accenture.hiltapplication.common.BaseViewModel
import com.accenture.hiltapplication.database.entity.UserEntity
import com.accenture.hiltapplication.repository.user.Repository
import kotlinx.coroutines.launch

class ThirdPageViewModel @ViewModelInject constructor(
    private val userRepository: Repository<UserEntity>
) : BaseViewModel() {

    fun onButtonClicked() {
        ioScope.launch {
            userRepository.refreshAll()
        }
    }

}