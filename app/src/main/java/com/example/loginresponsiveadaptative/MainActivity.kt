package com.example.loginresponsiveadaptative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginresponsiveadaptative.nav.Routes
import com.example.loginresponsiveadaptative.ui.theme.LoginResponsiveAdaptativeTheme
import com.example.loginresponsiveadaptative.view.ConfirmationView
import com.example.loginresponsiveadaptative.view.LoginView
import com.example.loginresponsiveadaptative.view.SigninView
import com.example.loginresponsiveadaptative.viewmodel.AdaptativeViewModel

class MainActivity : ComponentActivity() {
    val adaptativeViewModel: AdaptativeViewModel by viewModels<AdaptativeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            LoginResponsiveAdaptativeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Login.route
                    ) {
                        composable(Routes.Login.route) { LoginView(modifier = Modifier.padding(innerPadding), adaptativeViewModel, navController) }
                        composable(Routes.Signin.route) { SigninView(modifier = Modifier.padding(innerPadding), adaptativeViewModel, navController) }
                        composable(Routes.Confirmation.route) { ConfirmationView(modifier = Modifier.padding(innerPadding), navController = navController) }
                    }
                }
            }
        }
    }
}