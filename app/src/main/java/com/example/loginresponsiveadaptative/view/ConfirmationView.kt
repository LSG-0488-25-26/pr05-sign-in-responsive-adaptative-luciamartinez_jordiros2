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
import com.example.loginresponsiveadaptative.view.components.ScreenOrientation
import com.example.loginresponsiveadaptative.view.components.getWindowInfo
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun ConfirmationView(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val pantalla = getWindowInfo()

    if (pantalla.orientation == ScreenOrientation.PORTRAIT) {
        ConfirmationPortrait(modifier, navController)
    } else {
        ConfirmationLandscape(modifier, navController)
    }
}

@Composable
fun ConfirmationPortrait(
    modifier: Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BibliotecaBanner()

        Spacer(Modifier.height(32.dp))

        Icon(
            imageVector = Icons.Filled.CheckCircle,
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

@Composable
fun ConfirmationLandscape(
    modifier: Modifier,
    navController: NavController
) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
        ) {
            BibliotecaBanner()
        }

        Column(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Confirmado",
                modifier = Modifier.size(70.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(24.dp))

            Text(
                "¡Registro Exitoso!",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(12.dp))

            Text(
                "Cuenta creada correctamente.\nInicia sesión para comenzar.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(36.dp))

            Button(
                onClick = { navController.navigate(Routes.Login.route) },
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text("Continuar al Login")
            }
        }
    }
}