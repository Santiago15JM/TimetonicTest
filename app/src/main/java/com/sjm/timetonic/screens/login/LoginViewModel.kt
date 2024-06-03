package com.sjm.timetonic.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var showErrorDialog by mutableStateOf(false)

    fun login(onSuccess: () -> Unit) {
        // Field validations
        if (email.isBlank() || password.isBlank()) {
            showErrorDialog = true
            return
        }

        onSuccess()
    }
}
