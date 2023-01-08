package com.ngedev.thesisx.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = AuthScreen.Login.route,
        route = Graph.AUTH
    ) {
        composable(route = AuthScreen.Login.route) {

        }
        composable(route = AuthScreen.Register.route) {

        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
}