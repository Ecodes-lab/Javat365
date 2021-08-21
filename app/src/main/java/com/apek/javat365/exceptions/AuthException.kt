package com.apek.javat365.exceptions

import java.lang.Exception

data class AuthException (
    val status: String,
    override val message: String
) : Exception()