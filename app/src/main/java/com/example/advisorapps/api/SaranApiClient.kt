package com.example.advisorapps.api

import com.example.advisorapps.foremanSaran.ResponseSaran
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SaranApiClient {

    @FormUrlEncoded
    @POST("adv-web/Api/saran_create.php")
    fun postSaran(
        @Field("id_servis") id_servis : String,
        @Field("stnk") stnk : String,
        @Field("saran") saran : String

    ): Call<ResponseSaran>
}