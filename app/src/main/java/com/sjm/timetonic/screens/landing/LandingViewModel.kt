package com.sjm.timetonic.screens.landing

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sjm.timetonic.logic.API
import com.sjm.timetonic.logic.Book
import com.sjm.timetonic.logic.Preferences
import kotlinx.coroutines.launch

class LandingViewModel : ViewModel() {
    val books = mutableStateListOf<Book>()
    var showError by mutableStateOf(false)
    var loading by mutableStateOf(true)
    fun getBooks(ctx: Context) {
        viewModelScope.launch {
            val response = API.getAllBooks(Preferences.getUser(ctx)!!, Preferences.getSessionToken(ctx)!!)

            if (response == null || response.status != "ok") {
                showError = true
                return@launch
            }

            books.addAll(response.allBooks.books)
            loading = false
        }
    }

    fun logOut(ctx: Context, onLogOut: () -> Unit) {
        viewModelScope.launch {
            Preferences.clear(ctx)
            onLogOut()
        }
    }
}
