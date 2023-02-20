package com.example.advisorapps.api

import com.example.advisorapps.kendaraan.ResponseKendaraan
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface KendaraanApiClient {

    @FormUrlEncoded
    @POST("adv-web/Api/kendaraan_create.php")
    fun postKendaraan(
        @Field("stnk") stnk         : String,
        @Field("id_user") id_user   : String,
        @Field("model") model       : String,
        @Field("warna") warna       : String,
        @Field("tahun") tahun       : String,

    ): Call<ResponseKendaraan>

//    @GET("adv-web/Api/kendaraan.php")
//    fun getKendaraan() : Call<ArrayList<ResponseKendaraan>>
}