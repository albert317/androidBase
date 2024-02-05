package com.albert.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.albert.features.login.presentation.navigation.LoginScreen
import com.albert.features.login.presentation.navigation.loginNavGraph
import com.albert.features.splash.presentation.listUsers
import com.albert.features.splash.presentation.navigation.SplashNavigation
import com.albert.features.splash.presentation.navigation.SplashRoutes
import com.albert.features.splash.presentation.navigation.splashNavGraph

@Composable
fun MyNavigationNavHost(
    navController: NavHostController,
) {
    val startDestination = SplashNavigation.LOCAL_ROUTE
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        splashNavGraph(navController = navController) { token ->
            navController.navigate(LoginScreen.Login.route(token))
        }

        loginNavGraph(navController = navController) {
            /*navController.navigate(SplashNavigation.SPLASH_SCREEN) {
                popUpTo(SplashNavigation.SPLASH_SCREEN) { inclusive = true }
            }*/
            //navController.navigate(SplashNavigation.SPLASH_SCREEN)
            //navController.navigate(SplashNavigation.NAVIGATION)
            //navController.navigate(SplashNavigation.SPLASH_SCREEN)
            navController.navigate(SplashRoutes.Onboarding.route(listUsers))
        }
    }
}