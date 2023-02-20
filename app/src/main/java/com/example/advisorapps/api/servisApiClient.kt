package com.example.advisorapps.api

import com.example.advisorapps.dafServis.ResponseServis
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface servisApiClient {

    @FormUrlEncoded
    @POST("adv-web/Api/service_create.php")
    fun createservis(
        @Field("id_user") id_user : String,
        @Field("stnk") stnk : String,
        @Field("model") model : String,
        @Field("jenis_servis") jenis_servis : String,
        @Field("keluhan") keluhan : String

    ): Call<ResponseServis>

    @GET("adv-web/Api/services.php")
    fun getservis(): Call<ArrayList<ResponseServis>>
}