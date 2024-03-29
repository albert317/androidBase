package com.albert.features.login.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginRecoverPasswordScreen(
    email: String,
    onNextNavigationHome: () -> Unit,
) {
    Column {
        Text(text = "Recover password screen $email")
        Button(onClick = { onNextNavigationHome() }) {
            Text(text = "Click me")

        }
    }
}

@Preview
@Composable
fun LoginRecoverPasswordScreenPreview() {
    LoginRecoverPasswordScreen(
        email = "albert317@gmail.com",
        onNextNavigationHome = {}
    )
}