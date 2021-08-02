package com.accenture.hiltapplication.ui.main.page.third

import com.accenture.hiltapplication.common.BaseViewModel
import com.accenture.hiltapplication.database.entity.UserEntity
import com.accenture.hiltapplication.repository.user.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdPageViewModel @Inject constructor(
    private val userRepository: Repository<UserEntity>
) : BaseViewModel() {

    fun onButtonClicked() {
        ioScope.launch {
            userRepository.refreshAll()
        }
    }

}