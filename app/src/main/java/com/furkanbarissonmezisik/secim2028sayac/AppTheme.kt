package com.furkanbarissonmezisik.secim2028sayac

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ThemeState(initialDark: Boolean) {
    var isDark by mutableStateOf(true)

    fun toggleTheme() {
        isDark = !isDark
    }
}

@Composable
fun rememberThemeState(): ThemeState {
    val context = LocalContext.current
    val themePref = remember { ThemePreference(context) }
    val coroutineScope = rememberCoroutineScope()

    // DataStore'dan gelen değer
    val isDarkFlow = themePref.isDarkMode.collectAsState(initial = true) // default dark olsun

    val themeState = remember { ThemeState(isDarkFlow.value) }

    // Tema her değiştiğinde kaydedilsin
    LaunchedEffect(themeState.isDark) {
        themePref.saveDarkMode(themeState.isDark)
    }

    return themeState
}
