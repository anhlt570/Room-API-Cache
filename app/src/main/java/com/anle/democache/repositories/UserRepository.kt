package com.anle.democache.repositories

import androidx.lifecycle.LiveData
import com.anle.democache.App
import com.anle.democache.models.UserInfo
import com.anle.democache.net.apiService
import java.util.concurrent.Executors

class UserRepository {
    private val executors = Executors.newSingleThreadExecutor()
    private val userDao = App.db.value.getDao()

    fun getUsers() = userDao.getUsers()
    fun getUser(id: Int): LiveData<UserInfo> {
        executors.execute {
            if (System.currentTimeMillis() - userDao.getUser(id).lastRefreshed > 10000) {
                val response = apiService.getUser(id).execute()
                if (response.isSuccessful) {
                    response.body()?.let {
                        userDao.insertUser(it)
                    }
                }
            }
        }
        return userDao.getUserLiveData(id)
    }

    fun refreshUsers() {
        executors.execute {
            val response = apiService.getUsers().execute()
            if (response.isSuccessful) {
                response.body()?.let { books ->
                    userDao.insertUsers(books)
                }
            }
        }
    }

    fun clearUsers() {
        executors.execute {
            userDao.clearUsers()
        }
    }
}
