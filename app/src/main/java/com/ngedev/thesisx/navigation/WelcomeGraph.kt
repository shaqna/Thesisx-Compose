package com.ngedev.thesisx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.welcomeNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.WELCOME,
        startDestination = WelcomeScreen.Welcome.route
    ) {
        composable(route = WelcomeScreen.Welcome.route) {

        }
    }
}

sealed class WelcomeScreen(val route: String) {
    object Welcome: Screen("welcome_screen")
}