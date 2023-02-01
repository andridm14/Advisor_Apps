package com.example.advisorapps.api

import com.example.advisorapps.infoServis.ResponseInfo
import retrofit2.Call
import retrofit2.http.GET

interface InfoApiClient {

    @GET("adv-web/Api/pgj.php")
    fun getinfo():Call<ArrayList<ResponseInfo>>
}