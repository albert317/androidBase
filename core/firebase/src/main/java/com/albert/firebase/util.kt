package com.albert.firebase

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.tasks.await

object FirebaseMessagingManager {
    suspend fun getToken(): String? {
        return try {
            FirebaseMessaging.getInstance().token.await()
        } catch (e: Exception) {
            null
        }
    }
}


suspend fun setupRemoteConfig() {
    val remoteConfig = Firebase.remoteConfig
    val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3600 // Establece tu intervalo deseado
    }
    remoteConfig.setConfigSettingsAsync(configSettings)

    // Define los valores predeterminados de Remote Config
    remoteConfig.setDefaultsAsync(
        mapOf(
            "welcome_message" to "Welcome to our app!",
            "feature_enabled" to false
        )
    )

    // Fetch y activa los valores
    fetchAndActivate(remoteConfig)
}

fun fetchAndActivate(remoteConfig: FirebaseRemoteConfig) {
    remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val keyExample = remoteConfig.getString("keyExample")
            Log.d("RemoteConfig", "keyExample: $keyExample")
            // Usa estos valores como necesites en tu app
        } else {
            // Manejo de errores
        }
    }
}