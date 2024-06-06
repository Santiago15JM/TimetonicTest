package com.sjm.timetonic.screens.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sjm.timetonic.logic.API
import com.sjm.timetonic.logic.Preferences
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var showBadCredsDialog by mutableStateOf(false)
    var showErrorDialog by mutableStateOf(false)

    fun login(ctx: Context, onSuccess: () -> Unit) {
        // Field validations
        if (email.isBlank() || password.isBlank()) {
            showBadCredsDialog = true
            return
        }

        //Api calls
        viewModelScope.launch {
            try {
                val appKeyResponse = API.createAppKey()
                if (appKeyResponse == null || appKeyResponse.status != "ok") {
                    showErrorDialog = true
                    return@launch
                }

                val appKey = appKeyResponse.appkey
                val oauthKeyResponse = API.createOauthkey(email, password, appKey)

                if (oauthKeyResponse == null) {
                    showErrorDialog = true
                    return@launch
                }
                if (oauthKeyResponse.status != "ok") {
                    showBadCredsDialog = true
                    return@launch
                }

                val oauthKey = oauthKeyResponse.oauthkey
                val oauthUser = oauthKeyResponse.o_u
                Preferences.saveUser(ctx, oauthUser)
                val sessionKeyResponse = API.createSesskey(oauthUser, oauthKey)

                if (sessionKeyResponse == null || sessionKeyResponse.status != "ok") {
                    showErrorDialog = true
                    return@launch
                }

                val sessionToken = sessionKeyResponse.sesskey

                Preferences.saveSessionToken(ctx, sessionToken)

                onSuccess()
            } catch (e: Exception) {
                showErrorDialog = true
            }
        }
    }
}
