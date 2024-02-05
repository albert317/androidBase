package com.albert.features.login.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.albert.features.login.presentation.navigation.LoginNavigation.LOCAL_ROUTE
import com.albert.features.login.presentation.navigation.LoginNavigation.LOGIN_SCREEN
import com.albert.features.login.presentation.navigation.LoginNavigation.RECOVER_PASSWORD_SCREEN
import com.albert.features.login.presentation.ui.LoginRecoverPasswordScreen
import com.albert.features.login.presentation.ui.LoginScreen

private object LoginNavigation {
    const val LOCAL_ROUTE = "/loginNavigation"
    const val LOGIN_SCREEN = "loginScreen"
    const val RECOVER_PASSWORD_SCREEN = "recoverPasswordScreen"
}

private enum class NavArgs(val key: String) {
    ItemEmail("itemEmail"),
    ItemToken("itemToken")
}

sealed class LoginScreen(val route: String) {
    object Login : LoginScreen("$LOGIN_SCREEN/{${NavArgs.ItemToken.key}}") {
        fun route(token: String): String {
            return "$LOGIN_SCREEN/$token"
        }
    }

    object RecoverPassword : LoginScreen("$RECOVER_PASSWORD_SCREEN/{${NavArgs.ItemEmail.key}}") {
        fun route(itemId: String): String {
            return "$RECOVER_PASSWORD_SCREEN/$itemId"
        }
    }
}

fun NavGraphBuilder.loginNavGraph(
    navController: NavHostController,
    onNextNavigationHome: () -> Unit,
) {
    navigation(
        startDestination = LoginScreen.Login.route,
        route = LOCAL_ROUTE,
    ) {
        composable(
            LoginScreen.Login.route,
            arguments = listOf(navArgument(NavArgs.ItemToken.key) { type = NavType.StringType })
        ) {
            LoginScreen(
                token = it.arguments?.getString(NavArgs.ItemToken.key) ?: ""
            ) { email ->
                navController.navigate(LoginScreen.RecoverPassword.route(email))
            }
        }
        composable(
            LoginScreen.RecoverPassword.route,
            arguments = listOf(navArgument(NavArgs.ItemEmail.key) { type = NavType.StringType })
        ) { entry ->
            entry.arguments?.getString(NavArgs.ItemEmail.key)?.let { email ->
                LoginRecoverPasswordScreen(email) {
                    onNextNavigationHome()
                }
            }
        }
    }
}