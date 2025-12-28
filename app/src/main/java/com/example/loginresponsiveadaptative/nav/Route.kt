package com.example.loginresponsiveadaptative.nav

sealed class Routes(val route: String) {
    object Login: Routes("login")
    object Signin: Routes("signin")
    object Confirmation : Routes("confirmation")
}