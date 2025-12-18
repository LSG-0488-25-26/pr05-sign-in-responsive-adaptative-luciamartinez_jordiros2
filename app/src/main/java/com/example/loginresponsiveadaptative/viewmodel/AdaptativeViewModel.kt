package com.example.loginresponsiveadaptative.viewmodel

import android.util.Patterns
import androidx.core.text.isDigitsOnly
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
        }
        return false
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

    fun formatoCorreo(email: String): Boolean {
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun formatoTelefono(phone_text: String): Boolean {
        if (phone_text.length != 9 || !phone_text.isDigitsOnly()) {
            return true
        }
        return false
    }

    fun verificarContrasenya(password: String, confirmPassword: String): Boolean {
        if (password != confirmPassword) {
            return true
        }
        return false
    }
}