package com.sjm.timetonic.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.sjm.timetonic.ui.theme.TimetonicTestTheme

@Composable
fun LandingPage(nav: NavController, vm: LandingViewModel = viewModel()) {
    val ctx = LocalContext.current

    LaunchedEffect("books") {
        vm.getBooks(ctx)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Welcome",
                fontSize = 28.sp,
                modifier = Modifier.padding(horizontal = 10.dp),
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Button(
                onClick = {
                    vm.logOut(ctx, onLogOut = { nav.navigate("login") })
                },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ExitToApp,
                    contentDescription = "Log out"
                )
                Text(text = "Log out", fontSize = 20.sp)
            }
        }
        Row {
            Surface(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Your books",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                vm.loading -> item { CircularProgressIndicator() }

                vm.showError -> item { ErrorItem() }

                else -> {
                    items(vm.books) {
                        BookItem(title = it.ownerPrefs.title, imgPath = it.ownerPrefs.oCoverImg)
                    }
                    item { Spacer(modifier = Modifier.height(10.dp)) }
                }
            }
        }
    }
}

@Composable
fun BookItem(title: String, imgPath: String) {
    Surface(shape = RoundedCornerShape(10.dp)) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https://timetonic.com$imgPath",
                contentDescription = "Book cover for $title",
                modifier = Modifier
                    .size(250.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(text = title, textAlign = TextAlign.Center, fontSize = 20.sp)
        }
    }
}

@Composable
fun ErrorItem() {
    Surface(shape = RoundedCornerShape(10.dp)) {
        Column(
            modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Error icon")
            Text(text = "An error occurred loading your books")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    TimetonicTestTheme {
        LandingPage(rememberNavController())
    }
}
