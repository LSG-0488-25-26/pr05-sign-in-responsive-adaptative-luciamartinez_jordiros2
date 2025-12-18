package com.example.loginresponsiveadaptative.viewmodel

import androidx.lifecycle.ViewModel

class AdaptativeViewModel: ViewModel() {
    fun camposCompletos (
        name: String,
        birth: String,
        email: String,
        phone_text: String,
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (name == "" || birth == "" || email == "" || phone_text == "" || username == "" || password == "" || confirmPassword == "") {
            return true
        } else {
            return false
        }
    }

    fun formatoFecha(birth: String): Boolean {

    }
}