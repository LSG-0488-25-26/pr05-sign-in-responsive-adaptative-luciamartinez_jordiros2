package com.example.loginresponsiveadaptative.viewmodel

import androidx.lifecycle.ViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

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
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        formato.isLenient = false

        return try {
            formato.parse(birth)
            false
        } catch (e: ParseException) {
            true
        }
    }
}