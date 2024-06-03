package com.sjm.timetonic.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Login(nav: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
    ) {
        Text(text = "Timetonic", fontSize = 32.sp, fontWeight = FontWeight(600))

        OutlinedTextField(value = "", onValueChange = {}, placeholder = { Text(text = "Email")})
        OutlinedTextField(value = "", onValueChange = {}, placeholder = { Text(text = "Password")})

        Button(onClick = { nav.navigate("landing") }) {
            Text(text = "Log in")
        }
    }
}
