package com.accenture.hiltapplication.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "user")
@JsonClass(generateAdapter = true)
data class UserEntity(
    @PrimaryKey val id: String,
    val createdAt: String,
    val name: String,
    val avatar: String
)