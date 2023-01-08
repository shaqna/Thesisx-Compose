package com.ngedev.thesisx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.WELCOME
    ) {
        welcomeNavGraph(navController = navController)
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {

        }
    }
}