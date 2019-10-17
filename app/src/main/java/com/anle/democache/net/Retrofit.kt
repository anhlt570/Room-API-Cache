package com.anle.democache.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiService: ApiInterface = Retrofit.Builder()
    .baseUrl("http://5da7dc7823fa740014697a10.mockapi.io/")
    .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiInterface::class.java)