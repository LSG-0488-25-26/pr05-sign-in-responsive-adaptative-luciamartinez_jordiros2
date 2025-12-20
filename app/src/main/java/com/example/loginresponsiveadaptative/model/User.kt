package com.example.loginresponsiveadaptative.model

data class User(
    var name: String,
    var birth: String,
    var email: String,
    var phone: Int,
    val username: String,
    var password: String,
    var confirmPassword: String,
    var conditions: Boolean
)
