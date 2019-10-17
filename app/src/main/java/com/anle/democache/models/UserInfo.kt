package com.anle.democache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserInfo(
    @PrimaryKey
    var id: Int,
    var createdAt: String,
    var name: String,
    var avatar: String,
    var money: Int,
    var lastRefreshed: Long = System.currentTimeMillis()
)