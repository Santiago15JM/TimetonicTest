package com.sjm.timetonic.logic

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("session-details")

object Preferences {
    private const val SESSION_PREFERENCES_KEY = "session"
    private const val USER_PREFERENCES_KEY = "user"

    suspend fun saveSessionToken(ctx: Context, token: String) {
        ctx.dataStore.edit { prefs ->
            prefs[stringPreferencesKey(SESSION_PREFERENCES_KEY)] = token
        }
    }

    suspend fun getSessionToken(ctx: Context): String? =
        ctx.dataStore.data.first()[stringPreferencesKey(SESSION_PREFERENCES_KEY)]

    suspend fun saveUser(ctx: Context, token: String) {
        ctx.dataStore.edit { prefs ->
            prefs[stringPreferencesKey(USER_PREFERENCES_KEY)] = token
        }
    }

    suspend fun getUser(ctx: Context): String? =
        ctx.dataStore.data.first()[stringPreferencesKey(USER_PREFERENCES_KEY)]

    suspend fun clear(ctx: Context) {
        ctx.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
