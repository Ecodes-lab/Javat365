package com.apek.javat365.models

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @field:SerializedName("email") var email: String?,
    @field:SerializedName("code") var code: String?
)

