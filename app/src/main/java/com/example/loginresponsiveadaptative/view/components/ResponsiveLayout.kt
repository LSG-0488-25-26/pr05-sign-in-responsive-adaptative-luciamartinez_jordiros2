package com.example.loginresponsiveadaptative.view.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator

// Orientación (vetrical u horizonatal): ADAPTATIVE
enum class ScreenOrientation { PORTRAIT, LANDSCAPE }

// Data class que combina orientación y tamaño
// Guarda: ancho, alto y orientación
data class WindowInfo(
    // Ancho en dp: RESPONSIVE
    val width: Dp,
    // Alto en dp: RESPONSIVE
    val height: Dp,
    // Orientación: ADAPTATIVE
    val orientation: ScreenOrientation
)

// Obtener tamaño + orientación
@Composable
fun getWindowInfo(): WindowInfo {
    // Obtener context (info de app)
    val context = LocalContext.current

    // Medir ventana (RESPONSIVE) con la función de Google
    val windowMetrics = WindowMetricsCalculator.getOrCreate()
        // Devolver objeto con la info en pixeles
        .computeCurrentWindowMetrics(context)

    // Solo las medidas alto y ancho en pixeles
    val widthPx = windowMetrics.bounds.width().toFloat()
    val heightPx = windowMetrics.bounds.height().toFloat()
    // Cada pantalla tiene diferente densidad (más/menos píxeles por cm)
    // Para saber los DP --> es lo utilizado estándar
    // Más densidad, más calidad
    val density = context.resources.displayMetrics.density

    // Convertir píxeles a dp
    val widthDp = (widthPx / density).dp
    val heightDp = (heightPx / density).dp

    // Determinar orientación: ADAPTATIVE
    // ¿Cuál es la configuración actual del dispositivo?
    val configuration = LocalConfiguration.current
    // Si horizontal/Landscape:
    val orientation = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        // Orientación = horizontal
        ScreenOrientation.LANDSCAPE
    // Si no:
    } else {
        // Orientación = vertical
        ScreenOrientation.PORTRAIT
    }
    // Todo junto
    return WindowInfo(widthDp, heightDp, orientation)
}

// Padding: RESPONSIVE
@Composable
fun responsivePadding(): Dp {
    val windowInfo = getWindowInfo()
    return when {
        windowInfo.width < 360.dp -> 12.dp   // Móvil muy pequeño
        windowInfo.width < 480.dp -> 16.dp   // Móvil normal
        windowInfo.width < 600.dp -> 20.dp   // Móvil grande
        windowInfo.width < 840.dp -> 24.dp   // Tablet
        else -> 32.dp                        // Desktop
    }
}

// Tamaño de icono: RESPONSIVE
@Composable
fun responsiveIconSize(): Dp {
    val windowInfo = getWindowInfo()
    return when {
        windowInfo.width < 400.dp -> 24.dp   // Móvil pequeño
        windowInfo.width < 600.dp -> 32.dp   // Móvil
        windowInfo.width < 840.dp -> 40.dp   // Tablet
        else -> 48.dp                        // Desktop
    }
}

// Tamaño base para texto: RESPONSIVE
@Composable
fun responsiveTextSize(): Dp {
    val windowInfo = getWindowInfo()
    return when {
        windowInfo.width < 400.dp -> 14.dp   // Móvil pequeño
        windowInfo.width < 600.dp -> 16.dp   // Móvil
        windowInfo.width < 840.dp -> 18.dp   // Tablet
        else -> 20.dp                        // Desktop
    }
}

// Porcentaje de ancho para componentes: RESPONSIVE
@Composable
fun responsiveWidthFraction(): Float {
    val windowInfo = getWindowInfo()
    return when {
        windowInfo.width < 400.dp -> 1.0f    // 100% en móvil pequeño
        windowInfo.width < 600.dp -> 0.9f    // 90% en móvil
        windowInfo.width < 840.dp -> 0.7f    // 70% en tablet
        else -> 0.5f                         // 50% en desktop
    }
}