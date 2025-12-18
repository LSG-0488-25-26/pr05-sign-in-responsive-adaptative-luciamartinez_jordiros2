package com.example.loginresponsiveadaptative.model

import java.util.Date

data class Signin(
    var name: String,
    var birth: Date,
    var email: String,
    var phone: Int,
    var password: String,
    var confirmPassword: String,
    var conditions: Boolean
)
