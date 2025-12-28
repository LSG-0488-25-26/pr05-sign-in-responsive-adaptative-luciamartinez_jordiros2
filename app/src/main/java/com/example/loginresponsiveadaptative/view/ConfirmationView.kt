package com.example.loginresponsiveadaptative.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginresponsiveadaptative.nav.Routes
import com.example.loginresponsiveadaptative.view.components.BibliotecaBanner

@Composable
fun ConfirmationView(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BibliotecaBanner()

        Spacer(Modifier.height(32.dp))

        // Icono de check
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Confirmado",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(24.dp))

        Text(
            "¡Cuenta Creada!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        Text(
            "Tu registro se completó correctamente.\nYa puedes iniciar sesión.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(48.dp))

        Button(
            onClick = {
                // Volver al login
                navController.navigate(Routes.Login.route)
            },
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text("Ir al Login")
        }
    }
}