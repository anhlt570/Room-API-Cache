package com.anle.democache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserInfo(
    @PrimaryKey
    val id: Int,
    val createdAt: String,
    val name: String,
    val avatar: String,
    val money: Int,
    val lastRefreshed: Long = System.currentTimeMillis()
)