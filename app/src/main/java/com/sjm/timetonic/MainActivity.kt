package com.sjm.timetonic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sjm.timetonic.screens.landing.LandingPage
import com.sjm.timetonic.screens.login.Login
import com.sjm.timetonic.ui.theme.BlueGray
import com.sjm.timetonic.ui.theme.TimetonicTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimetonicTestTheme {
                val nav = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        color = BlueGray, modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        NavHost(
                            navController = nav,
                            startDestination = "login",
                        ) {
                            composable("login") { Login(nav) }
                            composable("landing") { LandingPage(nav) }
                        }
                    }
                }
            }
        }
    }
}
