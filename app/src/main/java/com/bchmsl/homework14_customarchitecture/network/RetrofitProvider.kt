package com.bchmsl.homework14_customarchitecture.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {
    private const val BASE_URL = "https://run.mocky.io/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun makeClient(): RetrofitClient = retrofit.create(RetrofitClient::class.java)

}