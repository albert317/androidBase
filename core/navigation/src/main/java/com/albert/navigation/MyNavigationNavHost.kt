package com.albert.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.albert.features.login.presentation.navigation.LoginNavigation
import com.albert.features.login.presentation.navigation.loginGraph
import com.albert.features.splash.presentation.navigation.SplashNavigation
import com.albert.features.splash.presentation.navigation.splashGraph

@Composable
fun MyNavigationNavHost(
    navController: NavHostController,
) {
    val startDestination = SplashNavigation.NAVIGATION
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        splashGraph(navController = navController) { token ->
            navController.navigate("${LoginNavigation.LOGIN_SCREEN_ROUTE}/$token")
        }
        loginGraph(navController = navController) {
            /*navController.navigate(SplashNavigation.SPLASH_SCREEN) {
                popUpTo(SplashNavigation.SPLASH_SCREEN) { inclusive = true }
            }*/
            //navController.navigate(SplashNavigation.SPLASH_SCREEN)
            //navController.navigate(SplashNavigation.NAVIGATION)
            //navController.navigate(SplashNavigation.SPLASH_SCREEN)
            navController.navigate("${SplashNavigation.ONBOARDING_SCREEN}/pedro")
        }
    }
}