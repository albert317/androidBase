package com.albert.features.splash.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OnboardingScreen(
    userName: String,
    onNextNavigationLogin: (String) -> Unit,
) {
    Column {
        Text(text = userName)
        Button(onClick = { onNextNavigationLogin("123456789123456782313123") }) {
            Text(text = "Click me")
        }
    }
}

