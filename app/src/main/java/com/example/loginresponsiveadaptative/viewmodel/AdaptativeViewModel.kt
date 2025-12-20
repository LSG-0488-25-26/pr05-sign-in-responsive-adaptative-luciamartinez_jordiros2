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

    // USUARIO FICTICIO PARA SIMULAR UNA BASE DE DATOS
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

    // INTRODUCE LOS DATOS DE LOGIN EN EL MODELO USER
    fun insertarComponentesLogin(email: String, password: String) {
        val currentLogin = _valorUser.value
        _valorUser.value = currentLogin.copy(
            email = email,
            password = password
        )
    }

    // RETORNA FALSE SI LOS DATOS DE LOGIN ESTAN INCOMPLETOS
    fun camposCompletosLogin(): Boolean {
        val currentLogin = _valorUser.value
        if (currentLogin.email == "" || currentLogin.password == "") {
            return false
        }
        return true
    }

    // RETORNA FALSE SI EL CORREO Y LA CONTRASEÑA DE LOGIN COHINCIDEN CON EL USUARIO REGISTRADO
    fun verificarUsuarioLogin(): Boolean {
        val currentLogin = _valorUser.value
        if (currentLogin.email != usuarioRegistrado.email || currentLogin.password != usuarioRegistrado.password) {
            return false
        }
        return true
    }

    // INTRODUCE LOS DATOS DE SIGNIN EN EL MODELO USER
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

    // RETORNA FALSE SI LOS DATOS DE SIGNIN ESTAN INCOMPLETOS
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

    // RETORNA FALSE SI EL FORMATO DE FECHA ES INVÁLIDO
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

    // RETORNA FALSE SI EL FORMATO CORREO ES INVÁLIDO
    fun formatoCorreo(): Boolean {
        val currentSignin = _valorUser.value
        return !Patterns.EMAIL_ADDRESS.matcher(currentSignin.email).matches()
    }

    // RETIRNA UN FALSE SI LAS CONTRASEÑAS NO COHINCIDEN
    fun verificarContrasenya(): Boolean {
        val currentSignin = _valorUser.value
        if (currentSignin.password != currentSignin.confirmPassword) {
            return true
        }
        return false
    }
}