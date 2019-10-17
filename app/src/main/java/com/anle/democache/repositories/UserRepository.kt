package com.anle.democache.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.anle.democache.App
import com.anle.democache.models.UserInfo
import com.anle.democache.net.apiService
import com.anle.democache.net.requests.UpdateUserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun updateUser(userInfo: UserInfo) {
        executors.execute {
            apiService.updateUser(
                UpdateUserRequest(
                    id = userInfo.id,
                    createdAt = userInfo.createdAt,
                    name = userInfo.name,
                    avatar = userInfo.avatar,
                    money = userInfo.money
                ), userInfo.id

            ).enqueue(object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Log.e("hahaha", "Api: Shit!!!")
                }

                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            executors.execute {
                                userDao.insertUser(it)
                            }
                        }
                    }
                }
            })
        }
    }
}
