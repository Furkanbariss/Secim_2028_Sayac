package com.furkanbarissonmezisik.secim2028sayac.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = LightBlue,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = DarkBackground, // Arka plan rengimizi burada belirtiyoruz
    surface = DarkBackground,     // Surface rengimizi de arka planla aynı yapabiliriz
    onPrimary = Color.Black,
    onSecondary = LightText,
    onTertiary = LightText,
    onBackground = Color.White,     // Metin rengimizi burada belirtiyoruz
    onSurface = LightText,         // Metin rengimizi burada belirtiyoruz
)

private val LightColorScheme = lightColorScheme(
    primary = ButtonBlue,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = lightBlack,          // ✅ düzeltildi
    surface = LightBackground,        // ✅ düzeltildi
    onPrimary = DarkText,             // ✅ buton içi yazılar
    onSecondary = DarkText,
    onTertiary = DarkText,
    onBackground = Color.Black,
    onSurface = DarkText


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Secim2028SayacTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}