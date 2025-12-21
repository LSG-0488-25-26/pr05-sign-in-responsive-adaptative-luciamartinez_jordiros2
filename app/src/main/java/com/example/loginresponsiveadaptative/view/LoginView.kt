package com.example.loginresponsiveadaptative.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.loginresponsiveadaptative.nav.Routes
import com.example.loginresponsiveadaptative.viewmodel.AdaptativeViewModel

@Composable
fun LoginView(modifier: Modifier, loginViewModel: AdaptativeViewModel, navController: NavController) {
    Button(
        onClick =  {
            navController.navigate(Routes.Signin.route)
        }
    ) {
        Text(text = "Crear cuenta")
    }
}