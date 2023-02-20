package com.example.advisorapps.api

import com.example.advisorapps.regist.ResponseRegist
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegistApiClient {

    @FormUrlEncoded
    @POST("adv-web/Api/users.create.php")
    fun regist(
        @Field("nm_user") nm_user   : String,
        @Field("username") username : String,
        @Field("password") password : String,
        @Field("role") role         : String,

    ): Call<ResponseRegist>
}