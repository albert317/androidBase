package com.albert.features.login.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    token: String,
    onNavigateToRecoverPassword: (String) -> Unit,
) {
    Column {
        Text(text = "Token: $token")
        TextField(value = "Login", onValueChange = { })
        Button(onClick = {
            onNavigateToRecoverPassword("Albert@gmail.com")
        }) {
            Text(text = "Click me")
        }
    }
}

