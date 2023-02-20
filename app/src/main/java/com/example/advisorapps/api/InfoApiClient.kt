package com.example.advisorapps.api

import com.example.advisorapps.infoServis.ResponseInfo
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface InfoApiClient {

    @GET("adv-web/Api/pgj.php")
    fun getinfo():Call<ArrayList<ResponseInfo>>

    @FormUrlEncoded
    @POST("adv-web/Api/pgj_create.php")
    fun addinfo(
        @Field("id_servis") id_servis : String?,
        @Field("pgj") pgj : String?

    ): Call<ResponseInfo>
}