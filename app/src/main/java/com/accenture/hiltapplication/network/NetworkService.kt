package com.accenture.hiltapplication.network

import com.accenture.hiltapplication.database.entity.UserEntity
import retrofit2.http.GET

interface NetworkService {
    @GET("users")
    suspend fun getUsers(): List<UserEntity>
}