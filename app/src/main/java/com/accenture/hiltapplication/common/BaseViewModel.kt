package com.accenture.hiltapplication.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel : ViewModel() {
    private val job by lazy { SupervisorJob() }
    protected val ioScope by lazy { CoroutineScope(job + Dispatchers.IO) }
}