package com.albert.features.login.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.albert.features.login.presentation.navigation.LoginNavigation.RECOVER_PASSWORD_SCREEN_ROUTE
import com.albert.features.login.presentation.navigation.LoginNavigation.LOGIN_SCREEN_ROUTE
import com.albert.features.login.presentation.navigation.LoginNavigation.ROOT_ROUTE
import com.albert.features.login.presentation.navigation.LoginArguments.EMAIL
import com.albert.features.login.presentation.navigation.LoginArguments.TOKEN
import com.albert.features.login.presentation.navigation.LoginRoutes.RECOVER_PASSWORD
import com.albert.features.login.presentation.navigation.LoginRoutes.LOGIN
import com.albert.features.login.presentation.ui.LoginRecoverPasswordScreen
import com.albert.features.login.presentation.ui.LoginScreen

object LoginNavigation {
    const val ROOT_ROUTE = "/loginNavigation"
    const val LOGIN_SCREEN_ROUTE: String = "loginScreen"
    const val RECOVER_PASSWORD_SCREEN_ROUTE: String = "recoverPasswordScreen"
}

object LoginArguments {
    const val TOKEN = "token"
    const val EMAIL = "email"
}

object LoginRoutes {
    const val LOGIN = "$LOGIN_SCREEN_ROUTE/{$TOKEN}"
    const val RECOVER_PASSWORD = "$RECOVER_PASSWORD_SCREEN_ROUTE/{$EMAIL}"
}

fun NavGraphBuilder.loginGraph(
    navController: NavHostController,
    onNextNavigationHome: () -> Unit,
) {
    navigation(
        startDestination = LOGIN,
        route = ROOT_ROUTE,
    ) {
        composable(
            LOGIN,
            arguments = listOf(navArgument(TOKEN) { type = NavType.StringType })
        ) {
            LoginScreen(
                token = it.arguments?.getString(TOKEN) ?: ""
            ) { email ->
                navController.navigate("$RECOVER_PASSWORD_SCREEN_ROUTE/$email")
            }
        }
        composable(
            RECOVER_PASSWORD,
            arguments = listOf(navArgument(EMAIL) { type = NavType.StringType })
        ) { entry ->
            entry.arguments?.getString(EMAIL)?.let { email ->
                LoginRecoverPasswordScreen(email) {
                    onNextNavigationHome()
                }
            }
        }
    }
}