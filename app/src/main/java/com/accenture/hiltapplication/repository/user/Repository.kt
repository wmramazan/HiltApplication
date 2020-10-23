package com.accenture.hiltapplication.repository.user

import androidx.lifecycle.LiveData
import com.accenture.hiltapplication.source.DataResult

interface Repository<T> {
    fun observeAll(): LiveData<DataResult<List<T>>>
    suspend fun getAll(forceUpdate: Boolean = false): DataResult<List<T>>
    suspend fun refreshAll()
}