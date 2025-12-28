package com.example.loginresponsiveadaptative.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginresponsiveadaptative.nav.Routes
import com.example.loginresponsiveadaptative.view.components.BibliotecaBanner
import com.example.loginresponsiveadaptative.view.components.ScreenOrientation
import com.example.loginresponsiveadaptative.view.components.getWindowInfo
import com.example.loginresponsiveadaptative.viewmodel.AdaptativeViewModel
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LoginView(
    modifier: Modifier,
    loginViewModel: AdaptativeViewModel,
    navController: NavController
) {
    // Estados para los campos
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    // Ver si está en vertical u horizontal
    val pantalla = getWindowInfo()

    if (pantalla.orientation == ScreenOrientation.PORTRAIT) {
        // VERTICAL
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp)
        ) {
            // Banner arriba
            BibliotecaBanner()
            Spacer(Modifier.height(32.dp))

            Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))

            // Campo Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // Campo Contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            // Mostrar error si hay
            if (mensajeError.isNotEmpty()) {
                Text(
                    text = mensajeError,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(Modifier.weight(1f))

            // Botones
            Button(
                onClick = { navController.navigate(Routes.Signin.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear cuenta")
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = {
                    // Usar el ViewModel
                    loginViewModel.insertarComponentesLogin(email, password)

                    if (!loginViewModel.camposCompletosLogin()) {
                        mensajeError = "Completa todos los campos"
                    } else if (!loginViewModel.verificarUsuarioLogin()) {
                        mensajeError = "Email o contraseña incorrectos"
                    } else {
                        mensajeError = "¡Login correcto!"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar sesión")
            }

            // Datos de prueba
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Prueba con:\nEmail: jorgex1412@gmail.com\nContraseña: 12345",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    } else {
        // HORIZONTAL
        Row(modifier = modifier.fillMaxSize()) {
            // Banner
            Box(modifier = Modifier.weight(0.25f).fillMaxHeight()) {
                BibliotecaBanner()
            }

            Column(
                modifier = Modifier
                    .weight(0.75f)
                    .fillMaxHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )

                    if (mensajeError.isNotEmpty()) {
                        Text(
                            text = mensajeError,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { navController.navigate(Routes.Signin.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Crear cuenta")
                    }

                    Spacer(Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = {
                            loginViewModel.insertarComponentesLogin(email, password)
                            if (!loginViewModel.camposCompletosLogin()) {
                                mensajeError = "Completa campos"
                            } else if (!loginViewModel.verificarUsuarioLogin()) {
                                mensajeError = "Credenciales incorrectas"
                            } else {
                                mensajeError = "¡Login correcto!"
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Iniciar sesión")
                    }
                }
            }
        }
    }
}