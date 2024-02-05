package com.albert.features.splash.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.albert.features.splash.presentation.listUsers
import com.albert.features.splash.presentation.model.User
import com.albert.features.splash.presentation.navigation.SplashNavigation.LOCAL_ROUTE
import com.albert.features.splash.presentation.navigation.SplashNavigation.ONBOARDING_SCREEN
import com.albert.features.splash.presentation.navigation.SplashNavigation.SPLASH_SCREEN
import com.albert.features.splash.presentation.ui.onboarding.OnboardingScreen
import com.albert.features.splash.presentation.ui.splash.SplashScreen
import com.albert.presentation.ParcelableListNavType
import com.albert.presentation.getParcelableArrayListNavType
import com.albert.presentation.toJson

object SplashNavigation {
    const val LOCAL_ROUTE = "/splash_navigation"
    const val SPLASH_SCREEN: String = "splash_screen"
    const val ONBOARDING_SCREEN: String = "onboarding_screen"
}

sealed class SplashRoutes(val route: String) {
    object Splash : SplashRoutes(SPLASH_SCREEN)
    object Onboarding : SplashRoutes("$ONBOARDING_SCREEN/{${SplashNavArgs.ListItems.key}}") {
        fun route(list: List<User>): String {
            return "$ONBOARDING_SCREEN/${list.toJson()}"
        }
    }
}

private enum class SplashNavArgs(val key: String) {
    ListItems("listItems"),
    ItemToken("itemToken")
}

fun NavGraphBuilder.splashNavGraph(
    navController: NavHostController,
    onNextNavigationLogin: (String) -> Unit,
) {
    navigation(
        startDestination = SplashRoutes.Splash.route,
        route = LOCAL_ROUTE
    ) {
        composable(route = SplashRoutes.Splash.route) {
            SplashScreen(
                onNextNavigationLogin = { userName ->
                    navController.navigate(SplashRoutes.Onboarding.route(listUsers))
                }
            )
        }
        composable(
            route = SplashRoutes.Onboarding.route,
            arguments = listOf(
                navArgument(SplashNavArgs.ListItems.key) {
                    type = ParcelableListNavType<User>()
                }
            )
        ) { entry ->
            val list: List<User> =
                entry.arguments?.getParcelableArrayListNavType(SplashNavArgs.ListItems.key)
                    ?: listOf()
            OnboardingScreen(
                userName = "name",
                user = User(
                    name = "Albert",
                    email = "", password = "dasd"
                ),
                list = list,
                onNextNavigationLogin = onNextNavigationLogin
            )
        }
    }
}
