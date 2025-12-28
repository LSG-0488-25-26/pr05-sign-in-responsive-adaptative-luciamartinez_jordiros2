package com.example.loginresponsiveadaptative.view.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.window.layout.WindowSizeClass
// Ancho
import androidx.window.layout.WindowWidthSizeClass
// Alto
import androidx.window.layout.WindowHeightSizeClass
import androidx.window.layout.computeWindowSizeClass

// Orientación (vetrical u horizonatal): ADAPTATIVE
enum class ScreenOrientation { PORTRAIT, LANDSCAPE }

// Data class que combina orientación y tamaño
// Guarda: ancho, alto y orientación
data class ScreenInfo(
    val widthClass: WindowWidthSizeClass,
    val heightClass: WindowHeightSizeClass,
    val orientation: ScreenOrientation
)

// Clasificación como el pdf del Raimón (para nosotros que somos cortitos)
// Definidos en la función: getDeviceLayout()
enum class DeviceLayout {
    PHONE_PORTRAIT,         // COMPACT × COMPACT, Portrait
    PHONE_LANDSCAPE,        // MEDIUM × COMPACT, Landscape
    TABLET_SMALL,           // MEDIUM × MEDIUM
    TABLET_LARGE_PORTRAIT,  // EXPANDED × MEDIUM, Portrait
    TABLET_LARGE_LANDSCAPE, // EXPANDED × MEDIUM, Landscape
    DESKTOP                 // EXPANDED × EXPANDED
}

// Calcular WindowSizeClass
// Función comoposable para ejecutarse con y basada en la UI
@Composable
fun calculateWindowSizeClass(): WindowSizeClass {
    // Obtener el 'context' (info de la app) para pasarlo a la función oficial de Google,
    // si no no furula
    val context = LocalContext.current
    // Obtener en formato estandar, no manualmente, en las unidades y umbrales correctos de las ventanas
    return computeWindowSizeClass(context)
}

// Función que calcula TODO
@Composable
fun getScreenInfo(): ScreenInfo {
    // Calcular tamaño width y height: COMPACT, MEDIUM, EXPANDED
    val windowSizeClass = calculateWindowSizeClass()

    // Calcular orientación: PORTRAIT, LANDSCAPE
    // LocalConfiguration.current --> te da la orientación actual
    val configuration = LocalConfiguration.current
    // Si horizontal/landscape:
    val orientation = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        // Guardar horizontal
        ScreenOrientation.LANDSCAPE
    // Si no:
    } else {
        // Guardar vertical
        ScreenOrientation.PORTRAIT
    }

    // Devolver info junta en un objeto
    return ScreenInfo(
        widthClass = windowSizeClass.widthSizeClass,
        heightClass = windowSizeClass.heightSizeClass,
        orientation = orientation
    )
}

// Traducir a nuestra clasificación decalarada en el enum DeviceLayout
@Composable
fun getDeviceLayout(): DeviceLayout {
    // Utilizar nuestra función de info como un objeto
    val screenInfo = getScreenInfo()

    // Teléfono vertical: COMPACT × COMPACT, Portrait
    if (screenInfo.widthClass == WindowWidthSizeClass.COMPACT &&
        screenInfo.heightClass == WindowHeightSizeClass.COMPACT &&
        screenInfo.orientation == ScreenOrientation.PORTRAIT) {
        // Si el objeto actual cumple con las características de este caso,
        // devuele el caso
        return DeviceLayout.PHONE_PORTRAIT
    }

    // Teléfono horizontal: MEDIUM × COMPACT, Landscape
    if (screenInfo.widthClass == WindowWidthSizeClass.MEDIUM &&
        screenInfo.heightClass == WindowHeightSizeClass.COMPACT &&
        screenInfo.orientation == ScreenOrientation.LANDSCAPE) {
        // Si el objeto actual cumple con las características de este caso,
        // devuele el caso
        return DeviceLayout.PHONE_LANDSCAPE
    }

    // Tablet pequeña / Foldable: MEDIUM × MEDIUM
    if (screenInfo.widthClass == WindowWidthSizeClass.MEDIUM &&
        screenInfo.heightClass == WindowHeightSizeClass.MEDIUM) {
        // Si el objeto actual cumple con las características de este caso,
        // devuele el caso
        return DeviceLayout.TABLET_SMALL
    }

    // Tablet grande vertical: EXPANDED × MEDIUM, Portrait
    if (screenInfo.widthClass == WindowWidthSizeClass.EXPANDED &&
        screenInfo.heightClass == WindowHeightSizeClass.MEDIUM &&
        screenInfo.orientation == ScreenOrientation.PORTRAIT) {
        // Si el objeto actual cumple con las características de este caso,
        // devuele el caso
        return DeviceLayout.TABLET_LARGE_PORTRAIT
    }

    // Tablet grande horizontal: EXPANDED × MEDIUM, Landscape
    if (screenInfo.widthClass == WindowWidthSizeClass.EXPANDED &&
        screenInfo.heightClass == WindowHeightSizeClass.MEDIUM &&
        screenInfo.orientation == ScreenOrientation.LANDSCAPE) {
        // Si el objeto actual cumple con las características de este caso,
        // devuele el caso
        return DeviceLayout.TABLET_LARGE_LANDSCAPE
    }

    // Desktop: EXPANDED × EXPANDED
    if (screenInfo.widthClass == WindowWidthSizeClass.EXPANDED &&
        screenInfo.heightClass == WindowHeightSizeClass.EXPANDED) {
        // Si el objeto actual cumple con las características de este caso,
        // devuele el caso
        return DeviceLayout.DESKTOP
    }

    // Caso por defecto (no tendría que pasar): teléfono vertical
    return DeviceLayout.PHONE_PORTRAIT
}