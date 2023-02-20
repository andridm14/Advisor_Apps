package com.example.advisorapps.api

import com.example.advisorapps.login.ResponseLogin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiClient {

    @FormUrlEncoded
    @POST("adv-web/Api/login.php")
    fun login(
        @Field("post_username") username : String,
        @Field("post_password") password : String
    ): Call<ResponseLogin>
}