package com.albert.features.splash.presentation.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.albert.features.splash.presentation.model.User

@Composable
fun OnboardingScreen(
    userName: String,
    user: User,
    list: List<User>,
    onNextNavigationLogin: (String) -> Unit,
) {
    Column {
        Text(text = user.name)
        Text(text = user.email)
        Text(text = user.password)
        Button(onClick = { onNextNavigationLogin("123456789123456782313123") }) {
            Text(text = "Click me")
        }
        LazyColumn(content = {
            items(list.size) { index ->
                Text(text = list[index].name)
                Text(text = list[index].email)
                Text(text = list[index].password)
                Spacer(modifier = Modifier.height(16.dp))
            }
        })
    }

}

