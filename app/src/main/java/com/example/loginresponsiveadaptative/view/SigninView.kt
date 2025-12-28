package com.example.loginresponsiveadaptative.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Composable
fun SigninView(
    modifier: Modifier,
    viewModel: AdaptativeViewModel,
    navController: NavController
) {
    // Estados para todos los campos
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmar by remember { mutableStateOf("") }
    var condiciones by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf("") }

    val pantalla = getWindowInfo()

    if (pantalla.orientation == ScreenOrientation.PORTRAIT) {
        // VERTICAL
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            BibliotecaBanner()
            Spacer(Modifier.height(24.dp))

            Text("Crear Cuenta", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))

            // Campos del formulario
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo*") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (dd/mm/yyyy)*") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ej: 31/08/2001") }
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email*") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono*") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario*") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña*") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = confirmar,
                onValueChange = { confirmar = it },
                label = { Text("Confirmar contraseña*") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(Modifier.height(16.dp))

            // Checkbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = condiciones,
                    onCheckedChange = { condiciones = it }
                )
                Spacer(Modifier.width(8.dp))
                Text("Acepto los términos")
            }

            // Mostrar error
            if (mensajeError.isNotEmpty()) {
                Text(
                    text = mensajeError,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(Modifier.weight(1f))

            // Botones
            Button(
                onClick = { navController.navigate(Routes.Login.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al login")
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = {
                    val telefonoNum = try { telefono.toInt() } catch (e: Exception) { 0 }

                    viewModel.insertarComponentesSignin(
                        nombre, fecha, email, telefonoNum, usuario, password, confirmar, condiciones
                    )

                    val resultado = viewModel.validarRegistroCompleto()

                    if (resultado == "OK") {
                        mensajeError = "¡Registro exitoso!"
                        navController.navigate(Routes.Confirmation.route)
                    } else {
                        mensajeError = resultado
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }
        }
    } else {
        // HORIZONTAL
        Row(modifier = modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(0.3f).fillMaxHeight()) {
                BibliotecaBanner()
            }

            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxHeight()
                    .padding(32.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text("Registro de Usuario", style = MaterialTheme.typography.headlineLarge)
                Spacer(Modifier.height(32.dp))

                // Mismos campos que en vertical pero más anchos
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre completo*") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha (dd/mm/yyyy)*") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email*") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono*") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuario*") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña*") },
                        modifier = Modifier.weight(1f),
                        visualTransformation = PasswordVisualTransformation()
                    )

                    OutlinedTextField(
                        value = confirmar,
                        onValueChange = { confirmar = it },
                        label = { Text("Confirmar*") },
                        modifier = Modifier.weight(1f),
                        visualTransformation = PasswordVisualTransformation()
                    )
                }

                Spacer(Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = condiciones,
                        onCheckedChange = { condiciones = it }
                    )
                    Spacer(Modifier.width(12.dp))
                    Text("Acepto términos y condiciones")
                }

                if (mensajeError.isNotEmpty()) {
                    Text(
                        text = mensajeError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                Spacer(Modifier.weight(1f))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(
                        onClick = { navController.navigate(Routes.Login.route) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Volver")
                    }

                    OutlinedButton(
                        onClick = {
                            val telefonoNum = try {
                                telefono.toInt()
                            } catch (e: Exception) {
                                0
                            }

                            viewModel.insertarComponentesSignin(
                                nombre, fecha, email, telefonoNum, usuario, password, confirmar, condiciones
                            )

                            if (viewModel.hayCamposVaciosSignin()) {
                                mensajeError = "Completa todos los campos"
                            } else if (viewModel.fechaEsInvalida()) {
                                mensajeError = "Fecha inválida"
                            } else if (viewModel.emailEsInvalido()) {
                                mensajeError = "Email inválido"
                            } else if (viewModel.contrasenyasNoCoinciden()) {
                                mensajeError = "Contraseñas no coinciden"
                            } else if (!condiciones) {
                                mensajeError = "Acepta los términos"
                            } else {
                                mensajeError = "¡Registro exitoso!"
                                navController.navigate(Routes.Confirmation.route)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Registrarse")
                    }
                }
            }
        }
    }
}