package com.anle.democache.ui.main

import androidx.lifecycle.ViewModel
import com.anle.democache.repositories.UserRepository


class UserListViewModel : ViewModel() {
    private val userRepository = UserRepository()
    val usersLiveData = userRepository.getUsers()

    fun refreshListUsers() {
        userRepository.refreshUsers()
    }

    fun clearUsers() {
        userRepository.clearUsers()
    }
}