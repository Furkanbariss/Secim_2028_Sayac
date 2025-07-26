package com.furkanbarissonmezisik.secim2028sayac

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// DataStore objesini Context'e bağlı hale getiriyoruz
val Context.dataStore by preferencesDataStore(name = "settings")

class ThemePreference(private val context: Context) {

    companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode_enabled")
    }

    // Tema durumu okunur. Eğer hiç veri yoksa true = koyu tema (ilk açılışta dark başlasın diye)
    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_MODE_KEY] ?: true  // <<< DİKKAT: Default = koyu tema
        }

    // Tema durumu değiştirilip kaydedilir
    suspend fun saveDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }
}
