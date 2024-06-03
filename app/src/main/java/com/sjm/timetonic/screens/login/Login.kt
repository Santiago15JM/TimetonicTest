package com.sjm.timetonic.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sjm.timetonic.ui.AlertDialog
import com.sjm.timetonic.ui.Button
import com.sjm.timetonic.ui.PasswordTextField

@Composable
fun Login(nav: NavController, vm: LoginViewModel = viewModel()) {
    Surface(shape = RoundedCornerShape(16.dp), modifier = Modifier.wrapContentSize()) {
        Column(
            modifier = Modifier.padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        ) {
            Text(text = "Timetonic Library", fontSize = 32.sp, fontWeight = FontWeight(600))

            OutlinedTextField(value = vm.email,
                onValueChange = { vm.email = it },
                label = { Text(text = "Email") })
            PasswordTextField(
                value = vm.password, onValueChange = { vm.password = it }, label = "Password"
            )

            Button(onClick = { vm.login(onSuccess = { nav.navigate("landing") }) }) {
                Text(text = "Log in", fontSize = 18.sp)
            }

            if (vm.showErrorDialog)
                AlertDialog(text = "Wrong credentials", onDismissRequest = {vm.showErrorDialog = false})
        }
    }
}
