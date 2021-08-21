package com.apek.javat365.api


import com.apek.javat365.models.LoginRequest
import com.apek.javat365.models.User
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("auth/email/login")
    fun userLogin(
        @Body email: LoginRequest
    ): Call<Void>

    @POST("auth/email/verify")
    fun userConfirmLogin(
        @Body jsonObject: LoginRequest
    ): Call<User>
}