package com.anle.democache.net

import com.anle.democache.models.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/user")
    fun getUsers(): Call<List<UserInfo>>

    @GET("/user/{id}")
    fun getUser(@Path("id") id: Int): Call<UserInfo>
}