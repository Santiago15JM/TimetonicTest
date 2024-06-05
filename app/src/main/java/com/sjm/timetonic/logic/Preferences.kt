package com.sjm.timetonic.logic

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("")

object Preferences {
    private const val SESSION_PREFERENCES_KEY = "session"
    suspend fun saveSessionToken(ctx: Context, token: String) {
        ctx.dataStore.edit { prefs ->
            prefs[stringPreferencesKey(SESSION_PREFERENCES_KEY)] = token
        }
    }

    suspend fun getSessionToken(ctx: Context): String? =
        ctx.dataStore.data.first()[stringPreferencesKey(SESSION_PREFERENCES_KEY)]

}
