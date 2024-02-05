package com.albert.features.splash.presentation.ui.splash

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.albert.firebase.FirebaseMessagingManager
import com.albert.firebase.setupRemoteConfig

@Composable
fun SplashScreen(
    onNextNavigationLogin: (String) -> Unit,
) {
    Column {
        Text(text = "SplashScreen")
        Button(onClick = { onNextNavigationLogin("Albert Montes") }) {
            Text(text = "Click me")
        }
    }

    var token by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        token = FirebaseMessagingManager.getToken()
        Log.d("SplashScreen", "Token: $token")
        setupRemoteConfig()
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        onNextNavigationLogin = {}
    )
}

