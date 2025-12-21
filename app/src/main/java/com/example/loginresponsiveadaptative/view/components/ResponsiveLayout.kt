package com.example.loginresponsiveadaptative.view.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.window.layout.WindowMetricsCalculator
import androidx.window.layout.WindowSizeClass
import androidx.window.layout.WindowWidthSizeClass
import androidx.window.layout.WindowHeightSizeClass
import androidx.window.layout.computeWindowSizeClass

// Orientación: ADAPTATIVE
enum class ScreenOrientation { PORTRAIT, LANDSCAPE }

// Data class que combina orientación y tamaño de lo de Google: COMPACT, MEDIUM, EXPANDED
data class ScreenInfo(
    val widthClass: WindowWidthSizeClass,
    val heightClass: WindowHeightSizeClass,
    val orientation: ScreenOrientation
)

// Clasificación como el pdf del Raimón para entendernos
enum class DeviceLayout {
    PHONE_PORTRAIT,         // COMPACT × COMPACT, Portrait
    PHONE_LANDSCAPE,        // MEDIUM × COMPACT, Landscape
    TABLET_SMALL,           // MEDIUM × MEDIUM
    TABLET_LARGE_PORTRAIT,  // EXPANDED × MEDIUM, Portrait
    TABLET_LARGE_LANDSCAPE, // EXPANDED × MEDIUM, Landscape
    DESKTOP                 // EXPANDED × EXPANDED
}

// Función para WindowSizeClass
@Composable
fun calculateWindowSizeClass(): WindowSizeClass {
    val context = LocalContext.current
    return computeWindowSizeClass(context)
}

// Función que calcula TODO
@Composable
fun getScreenInfo(): ScreenInfo {
    // WindowSizeClass
    val windowSizeClass = calculateWindowSizeClass()

    // Orientación
    val configuration = LocalConfiguration.current
    val orientation = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        ScreenOrientation.LANDSCAPE
    } else {
        ScreenOrientation.PORTRAIT
    }

    return ScreenInfo(
        widthClass = windowSizeClass.widthSizeClass,
        heightClass = windowSizeClass.heightSizeClass,
        orientation = orientation
    )
}

// Traducir a nuestra clasificación
@Composable
fun getDeviceLayout(): DeviceLayout {
    val screenInfo = getScreenInfo()

    return when {
        // Teléfono vertical: COMPACT × COMPACT, Portrait
        screenInfo.widthClass == WindowWidthSizeClass.COMPACT &&
                screenInfo.heightClass == WindowHeightSizeClass.COMPACT &&
                screenInfo.orientation == ScreenOrientation.PORTRAIT ->
            DeviceLayout.PHONE_PORTRAIT

        // Teléfono horizontal: MEDIUM × COMPACT, Landscape
        screenInfo.widthClass == WindowWidthSizeClass.MEDIUM &&
                screenInfo.heightClass == WindowHeightSizeClass.COMPACT &&
                screenInfo.orientation == ScreenOrientation.LANDSCAPE ->
            DeviceLayout.PHONE_LANDSCAPE

        // Tablet pequeña / Foldable: MEDIUM × MEDIUM
        screenInfo.widthClass == WindowWidthSizeClass.MEDIUM &&
                screenInfo.heightClass == WindowHeightSizeClass.MEDIUM ->
            DeviceLayout.TABLET_SMALL

        // Tablet grande vertical: EXPANDED × MEDIUM, Portrait
        screenInfo.widthClass == WindowWidthSizeClass.EXPANDED &&
                screenInfo.heightClass == WindowHeightSizeClass.MEDIUM &&
                screenInfo.orientation == ScreenOrientation.PORTRAIT ->
            DeviceLayout.TABLET_LARGE_PORTRAIT

        // Tablet grande horizontal: EXPANDED × MEDIUM, Landscape
        screenInfo.widthClass == WindowWidthSizeClass.EXPANDED &&
                screenInfo.heightClass == WindowHeightSizeClass.MEDIUM &&
                screenInfo.orientation == ScreenOrientation.LANDSCAPE ->
            DeviceLayout.TABLET_LARGE_LANDSCAPE

        // Desktop: EXPANDED × EXPANDED
        screenInfo.widthClass == WindowWidthSizeClass.EXPANDED &&
                screenInfo.heightClass == WindowHeightSizeClass.EXPANDED ->
            DeviceLayout.DESKTOP

        // Casos por defecto por si acaso
        else -> when (screenInfo.widthClass) {
            WindowWidthSizeClass.COMPACT -> DeviceLayout.PHONE_PORTRAIT
            WindowWidthSizeClass.MEDIUM -> DeviceLayout.TABLET_SMALL
            WindowWidthSizeClass.EXPANDED -> DeviceLayout.TABLET_LARGE_LANDSCAPE
        }
    }
}

// "MAIN"
@Composable
fun AdaptiveResponsiveContent(
    phonePortrait: @Composable () -> Unit,
    phoneLandscape: @Composable () -> Unit,
    tabletSmall: @Composable () -> Unit,
    tabletLargePortrait: @Composable () -> Unit,
    tabletLargeLandscape: @Composable () -> Unit,
    desktop: @Composable () -> Unit
) {
    when (getDeviceLayout()) {
        DeviceLayout.PHONE_PORTRAIT -> phonePortrait()
        DeviceLayout.PHONE_LANDSCAPE -> phoneLandscape()
        DeviceLayout.TABLET_SMALL -> tabletSmall()
        DeviceLayout.TABLET_LARGE_PORTRAIT -> tabletLargePortrait()
        DeviceLayout.TABLET_LARGE_LANDSCAPE -> tabletLargeLandscape()
        DeviceLayout.DESKTOP -> desktop()
    }
}