package com.albert.features.splash.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.albert.features.splash.presentation.navigation.SplashArguments.USER_NAME_ARG
import com.albert.features.splash.presentation.navigation.SplashRoutes.ONBOARDING_ROUTE
import com.albert.features.splash.presentation.navigation.SplashNavigation.NAVIGATION
import com.albert.features.splash.presentation.navigation.SplashNavigation.ONBOARDING_SCREEN
import com.albert.features.splash.presentation.navigation.SplashNavigation.SPLASH_SCREEN
import com.albert.features.splash.presentation.ui.OnboardingScreen
import com.albert.features.splash.presentation.ui.SplashScreen

object SplashNavigation {
    const val NAVIGATION = "/splash_navigation"
    const val SPLASH_SCREEN: String = "splash_screen"
    const val ONBOARDING_SCREEN: String = "onboarding_screen"
}

object SplashArguments {
    const val USER_NAME_ARG = "userName"
}

object SplashRoutes {
    const val ONBOARDING_ROUTE = "$ONBOARDING_SCREEN/{$USER_NAME_ARG}"
}

fun NavGraphBuilder.splashGraph(
    navController: NavHostController,
    onNextNavigationLogin: (String) -> Unit,
) {
    navigation(
        startDestination = SPLASH_SCREEN,
        route = NAVIGATION
    ) {
        composable(SPLASH_SCREEN) {
            SplashScreen(
                onNextNavigationLogin = { userName ->
                    navController.navigate("$ONBOARDING_SCREEN/$userName")
                }
            )
        }
        composable(
            ONBOARDING_ROUTE,
            arguments = listOf(navArgument(USER_NAME_ARG) { type = NavType.StringType })
        ) { entry ->
            entry.arguments?.getString(USER_NAME_ARG)?.let {
                OnboardingScreen(
                    userName = it,
                    onNextNavigationLogin = onNextNavigationLogin
                )
            }
        }
    }
}
