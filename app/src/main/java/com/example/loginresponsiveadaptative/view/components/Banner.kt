package com.example.loginresponsiveadaptative.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Cambiar banner automáticamente cuando se gire el dispositivo
@Composable
fun BibliotecaBanner() {
    // Obtener información completa de la pantalla, ResponsiveLayout.kt --> getWindowInfo()
    val windowInfo = getWindowInfo()

    // ADAPTATIVE: Cambia layout según orientación
    // Si vertical --> banner horizontal arriba
    if (windowInfo.orientation == ScreenOrientation.PORTRAIT) {
        BannerParaVertical(windowInfo.width)
        // Si no --> banner vertical lateral
    } else {
        BannerParaHorizontal(windowInfo.width)
    }
}

// Banner para pantallas VERTICALES (PORTRAIT)
@Composable
private fun BannerParaVertical(screenWidth: Dp) {
    // Fila horizontal con icono y textos
    Row(
        modifier = Modifier
            // RESPONSIVE: Ocupa el 100% del ancho
            .fillMaxWidth()
            // Espaciado interno de 16dp en todos los lados
            .padding(16.dp),
        // Centra verticalmente los elementos
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            // Estrella como icono
            Icons.Filled.Star,
            // Texto para accesibilidad (lectores de pantalla)
            contentDescription = "Logo Biblioteca",
            // Tamaño fijo de 32dp
            modifier = Modifier.size(32.dp),
            // Color del tema principal
            tint = MaterialTheme.colorScheme.primary
        )

        // Icono y textos espaciados horizontal
        Spacer(Modifier.width(12.dp))

        // Columna con nombre y despcipción
        Column {
            // Nombre de la biblio
            Text(
                // Nombre de la app
                "BIBLIOTECA+",
                // Título grande
                style = MaterialTheme.typography.titleLarge,
                // Texto en negrita
                fontWeight = FontWeight.Bold,
                // Mismo color que el icono
                color = MaterialTheme.colorScheme.primary
            )
            // Descripción
            Text(
                // Frase que define la app
                "El conocimiento a tu alcance",
                // Tipografía normal para la descripción
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

/// Banner para pantallas HORIZONTALES (LANDSCAPE)
@Composable
private fun BannerParaHorizontal(screenWidth: Dp) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Logo Biblioteca Digital",
            modifier = Modifier.size(42.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(16.dp))

        Text(
            "BIBLIOTECA+",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "Tu portal",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}