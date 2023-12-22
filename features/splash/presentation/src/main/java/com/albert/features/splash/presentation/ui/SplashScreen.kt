package com.albert.features.splash.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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
}

