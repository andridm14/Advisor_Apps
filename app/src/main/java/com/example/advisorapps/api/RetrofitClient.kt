package com.example.advisorapps.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.100.57/")
//            .baseUrl("http://172.20.10.4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): LoginApiClient{
        return getRetrofitClient().create(LoginApiClient::class.java)
    }
    fun getInfo(): InfoApiClient{
        return getRetrofitClient().create(InfoApiClient::class.java)
    }
    fun getPem(): PemApiClient{
        return getRetrofitClient().create(PemApiClient::class.java)
    }
    fun getLap(): LapApiClient{
        return getRetrofitClient().create(LapApiClient::class.java)
    }
    fun createServis(): servisApiClient{
        return getRetrofitClient().create(servisApiClient::class.java)
    }
    fun register(): RegistApiClient{
        return getRetrofitClient().create(RegistApiClient::class.java)
    }
    fun createKendaraan(): KendaraanApiClient{
        return getRetrofitClient().create(KendaraanApiClient::class.java)
    }
    fun createSaran(): SaranApiClient{
        return getRetrofitClient().create(SaranApiClient::class.java)
    }
}