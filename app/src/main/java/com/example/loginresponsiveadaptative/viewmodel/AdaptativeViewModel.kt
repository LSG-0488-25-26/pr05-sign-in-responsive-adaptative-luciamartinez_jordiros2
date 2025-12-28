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
    private val usuarioRegistrado = User(
        "Jordi Ros López",
        "31/08/2001",
        "jorgex1412@gmail.com",
        674490954,
        "Darky12",
        "12345",
        "12345",
        true
    )

    // INTRODUCE LOS DATOS DE LOGIN EN EL MODELO USER
    fun insertarComponentesLogin(email: String, password: String) {
        _valorUser.value = _valorUser.value?.copy(
            email = email,
            password = password
        )
    }

    // RETORNA TRUE SI LOS DATOS DE LOGIN ESTAN COMPLETOS
    fun camposCompletosLogin(): Boolean {
        val currentLogin = _valorUser.value
        return !(currentLogin?.email.isNullOrEmpty() || currentLogin?.password.isNullOrEmpty())
    }

    // RETORNA TRUE SI EL LOGIN ES CORRECTO
    fun verificarUsuarioLogin(): Boolean {
        val currentLogin = _valorUser.value
        return currentLogin?.email == usuarioRegistrado.email &&
                currentLogin?.password == usuarioRegistrado.password
    }

    // INTRODUCE LOS DATOS DE SIGNIN EN EL MODELO USER
    fun insertarComponentesSignin(name: String, birth: String, email: String, phone: Int, username: String, password: String, confirmPassword: String, conditions: Boolean) {
        _valorUser.value = _valorUser.value?.copy(
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

    // RETORNA TRUE SI LOS DATOS DE SIGNIN ESTAN INCOMPLETOS
    fun hayCamposVaciosSignin(): Boolean {
        val currentSignin = _valorUser.value
        return currentSignin?.name.isNullOrEmpty() ||
                currentSignin?.birth.isNullOrEmpty() ||
                currentSignin?.email.isNullOrEmpty() ||
                currentSignin?.username.isNullOrEmpty() ||
                currentSignin?.password.isNullOrEmpty() ||
                currentSignin?.confirmPassword.isNullOrEmpty()
    }

    // RETORNA TRUE SI EL FORMATO DE FECHA ES INVÁLIDO
    fun fechaEsInvalida(): Boolean {
        val currentSignin = _valorUser.value
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        formato.isLenient = false

        return try {
            formato.parse(currentSignin?.birth ?: "")
            false
        } catch (e: ParseException) {
            true
        }
    }

    // RETORNA TRUE SI EL FORMATO CORREO ES INVÁLIDO
    fun emailEsInvalido(): Boolean {
        val currentSignin = _valorUser.value
        return !Patterns.EMAIL_ADDRESS.matcher(currentSignin?.email ?: "").matches()
    }

    // RETORNA TRUE SI LAS CONTRASEÑAS NO COINCIDEN
    fun contrasenyasNoCoinciden(): Boolean {
        val currentSignin = _valorUser.value
        return currentSignin?.password != currentSignin?.confirmPassword
    }

    fun telefonoEsInvalido(): Boolean {
        val currentSignin = _valorUser.value
        val telefono = currentSignin?.phone ?: 0
        return telefono <= 0 || telefono.toString().length < 9
    }

    // FUNCIÓN EXTRA PARA VALIDAR TODO EL REGISTRO
    fun validarRegistroCompleto(): String {
        return when {
            hayCamposVaciosSignin() -> "Completa todos los campos"
            fechaEsInvalida() -> "Fecha inválida (dd/mm/yyyy)"
            emailEsInvalido() -> "Email inválido"
            telefonoEsInvalido() -> "Teléfono inválido (mínimo 9 dígitos)"
            contrasenyasNoCoinciden() -> "Las contraseñas no coinciden"
            _valorUser.value?.conditions != true -> "Acepta los términos y condiciones"
            (_valorUser.value?.password?.length ?: 0) < 5 -> "Contraseña muy corta (mínimo 5)"
            else -> "OK"
        }
    }
}