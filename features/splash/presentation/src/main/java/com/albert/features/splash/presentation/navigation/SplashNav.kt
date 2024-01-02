package com.albert.features.splash.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.albert.features.splash.presentation.listUsers
import com.albert.features.splash.presentation.model.User
import com.albert.features.splash.presentation.navigation.SplashArguments.LIST
import com.albert.features.splash.presentation.navigation.SplashArguments.USER_NAME_ARG
import com.albert.features.splash.presentation.navigation.SplashNavigation.NAVIGATION
import com.albert.features.splash.presentation.navigation.SplashNavigation.ONBOARDING_SCREEN
import com.albert.features.splash.presentation.navigation.SplashNavigation.SPLASH_SCREEN
import com.albert.features.splash.presentation.navigation.SplashRoutes.ONBOARDING_ROUTE
import com.albert.features.splash.presentation.ui.OnboardingScreen
import com.albert.features.splash.presentation.ui.SplashScreen
import com.google.gson.Gson

object SplashNavigation {
    const val NAVIGATION = "/splash_navigation"
    const val SPLASH_SCREEN: String = "splash_screen"
    const val ONBOARDING_SCREEN: String = "onboarding_screen"
}

object SplashArguments {
    const val USER_NAME_ARG = "userName"
    const val USER = "user"
    const val LIST = "list"
}

object SplashRoutes {
    const val ONBOARDING_ROUTE = "$ONBOARDING_SCREEN/{$LIST}"
}

fun NavGraphBuilder.splashGraph(
    navController: NavHostController,
    onNextNavigationLogin: (String) -> Unit,
) {
    navigation(
        startDestination = SPLASH_SCREEN,
        route = NAVIGATION
    ) {
        val userDemo = User(
            name = "Albert",
            email = "albert317@gmail.com",
            password = "fdsfds"
        )
        composable(SPLASH_SCREEN) {
            SplashScreen(
                onNextNavigationLogin = { userName ->
                    val gson = Gson()
                    val listJson = gson.toJson(listUsers) // Usando Gson para convertir a JSON
                    navController.navigate("$ONBOARDING_SCREEN/$listJson")
                }
            )
        }
        composable(
            ONBOARDING_ROUTE,
            arguments = listOf(
                // navArgument(USER_NAME_ARG) { type = NavType.StringType },
                //navArgument(USER) { type = NavType.StringType },
                navArgument(LIST) { type = NavType.StringType }
            )
        ) { entry ->

            val gson = Gson()
            // val userJson = entry.arguments?.getString(USER)
            // val user = gson.fromJson(userJson, User::class.java)
            val listJson = entry.arguments?.getString(LIST)
            val list = gson.fromJson(listJson, Array<User>::class.java).toList()


            //entry.arguments?.getString(USER_NAME_ARG)?.let {
                OnboardingScreen(
                    userName = "name",
                    user = User(
                        name = "Albert",
                        email = "", password = "dasd"
                    ),
                    list = list,
                    onNextNavigationLogin = onNextNavigationLogin
                )
            //}
        }
    }


}
