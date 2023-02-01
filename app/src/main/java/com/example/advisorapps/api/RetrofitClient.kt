package com.example.advisorapps.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.100.57/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): LoginApiClient {
        return getRetrofitClient().create(LoginApiClient::class.java)
    }
    fun getInfo(): InfoApiClient{
        return getRetrofitClient().create(InfoApiClient::class.java)
    }
    fun getPem(): PemApiClient{
        return getRetrofitClient().create(PemApiClient::class.java)
    }
}