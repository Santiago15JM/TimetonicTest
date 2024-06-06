package com.sjm.timetonic.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sjm.timetonic.R
import com.sjm.timetonic.ui.AlertDialog
import com.sjm.timetonic.ui.Button
import com.sjm.timetonic.ui.PasswordTextField
import com.sjm.timetonic.ui.theme.TimetonicTestTheme

@Composable
fun Login(nav: NavController, vm: LoginViewModel = viewModel()) {
    val ctx = LocalContext.current

    Surface(
        shape = RoundedCornerShape(16.dp), modifier = Modifier
            .wrapContentSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        ) {

            Surface(color = Color.LightGray, shape = CircleShape) {
                Icon(
                    painter = painterResource(R.drawable.person),
                    contentDescription = "user icon",
                    modifier = Modifier
                        .size(96.dp)
                        .padding(20.dp)
                )
            }
            Text(text = "Timetonic Library", fontSize = 32.sp, fontWeight = FontWeight(600))

            OutlinedTextField(value = vm.email,
                onValueChange = { if (!it.contains(' ')) vm.email = it },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            PasswordTextField(
                value = vm.password,
                onValueChange = { if (!it.contains(' ')) vm.password = it },
                label = "Password",
                onDone = { vm.login(ctx, onSuccess = { nav.navigate("landing") }) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {
                vm.login(ctx, onSuccess = {
                    // Navigate to landing and clear back stack
                    nav.navigate("landing") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                })
            }) {
                Text(text = "Log in", fontSize = 18.sp)
            }

            // Conditions for showing dialogs
            when {
                vm.showBadCredsDialog -> AlertDialog(text = "Wrong credentials",
                    onDismissRequest = { vm.showBadCredsDialog = false })

                vm.showErrorDialog -> AlertDialog(text = "There was an error",
                    onDismissRequest = { vm.showErrorDialog = false })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    TimetonicTestTheme {
        Login(rememberNavController())
    }
}
