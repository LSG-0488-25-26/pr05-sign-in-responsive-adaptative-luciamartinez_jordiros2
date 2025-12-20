package com.example.loginresponsiveadaptative.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginresponsiveadaptative.model.User
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class AdaptativeViewModel: ViewModel() {
    private val _valorUser = MutableLiveData(User("", "", "", 0, "", "", "", false))

    var usuarioRegistrado = User(
        "Jordi Ros López",
        "31/08/2001",
        "jorgex1412@gmail.com",
        674490954,
        "Darky12",
        "12345",
        "12345",
        false
    )

    fun insertarComponentesLogin(email: String, password: String) {
        val currentLogin = _valorUser.value
        _valorUser.value = currentLogin.copy(
            email = email,
            password = password
        )
    }

    fun camposCompletosLogin(): Boolean {
        val currentLogin = _valorUser.value
        if (currentLogin.email == "" || currentLogin.password == "") {
            return false
        }
        return true
    }

    fun verificarUsuarioLogin(): Boolean {
        val currentLogin = _valorUser.value
        if (currentLogin.email == usuarioRegistrado.email && currentLogin.password == usuarioRegistrado.password) {
            return true
        }
        return false
    }

    fun insertarComponentesSignin(name: String, birth: String, email: String, phone: Int, username: String, password: String, confirmPassword: String, conditions: Boolean) {
        val currentSignin = _valorUser.value
        _valorUser.value = currentSignin.copy(
            name = name,
            birth = birth,
            email = email,
            phone = phone,
            username = username,
            password = password,
            confirmPassword = confirmPassword,
            conditions = conditions
        )
    }

    fun camposCompletosSignin(): Boolean {
        val currentSignin = _valorUser.value
        if (currentSignin.name == "" ||
            currentSignin.birth == "" ||
            currentSignin.email == "" ||
            currentSignin.username == "" ||
            currentSignin.password == "" ||
            currentSignin.confirmPassword == ""
        ) {
            return true
        }
        return false
    }

    fun formatoFecha(): Boolean {
        val currentSignin = _valorUser.value
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        formato.isLenient = false

        return try {
            formato.parse(currentSignin.birth)
            false
        } catch (e: ParseException) {
            true
        }
    }

    fun formatoCorreo(): Boolean {
        val currentSignin = _valorUser.value
        return !Patterns.EMAIL_ADDRESS.matcher(currentSignin.email).matches()
    }

    fun verificarContrasenya(): Boolean {
        val currentSignin = _valorUser.value
        if (currentSignin.password != currentSignin.confirmPassword) {
            return true
        }
        return false
    }
}