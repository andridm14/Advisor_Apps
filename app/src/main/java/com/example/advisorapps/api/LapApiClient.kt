package com.example.advisorapps.api

import com.example.advisorapps.lapServis.ResponseLap
import retrofit2.Call
import retrofit2.http.GET

interface LapApiClient {

    @GET("adv-web/Api/laporan.php")
    fun getlap(): Call<ArrayList<ResponseLap>>
}