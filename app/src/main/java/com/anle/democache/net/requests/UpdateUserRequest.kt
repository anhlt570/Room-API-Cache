package com.anle.democache.net.requests


data class UpdateUserRequest(
    var id: Int,
    var createdAt: String,
    var name: String,
    var avatar: String,
    var money: Int
)