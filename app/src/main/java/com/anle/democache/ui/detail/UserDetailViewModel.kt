package com.anle.democache.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anle.democache.models.UserInfo
import com.anle.democache.repositories.UserRepository

class UserDetailViewModel : ViewModel() {
    lateinit var userLiveData: LiveData<UserInfo>
    private val userRepository = UserRepository()
    fun init(id: Int) {
        userLiveData = userRepository.getUser(id)
    }

    fun updateMoney(money:Int) {
        userLiveData.value?.let {
            userRepository.updateUser(it.apply {
                this.money = money
            })
        }
    }
}