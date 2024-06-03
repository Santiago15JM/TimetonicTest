package com.sjm.timetonic.screens.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LandingPage() {
    Column {
        Row(modifier = Modifier.padding(20.dp)) {
            Text(text = "Welcome", fontSize = 24.sp)
        }
        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
            item {
                Text(text = "Book 1")
            }
        }
    }
}