package com.anle.democache.net

import com.anle.democache.models.UserInfo
import com.anle.democache.net.requests.UpdateUserRequest
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("/user")
    fun getUsers(): Call<List<UserInfo>>

    @GET("/user/{id}")
    fun getUser(@Path("id") id: Int): Call<UserInfo>

    @PUT("/user/{id}")
    fun updateUser(@Body user: UpdateUserRequest,@Path("id") id: Int): Call<UserInfo>
}