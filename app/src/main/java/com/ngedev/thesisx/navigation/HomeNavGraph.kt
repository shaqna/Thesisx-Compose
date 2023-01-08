package com.ngedev.thesisx.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ngedev.thesisx.R

@Composable
fun HomeNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.HOME
    ) {
        composable(route = BottomBarScreen.Home.route){

        }
        composable(route = BottomBarScreen.Search.route){

        }
        composable(route = BottomBarScreen.Favorite.route){

        }
        composable(route = BottomBarScreen.Loan.route){

        }
        composable(route = BottomBarScreen.Settings.route){

        }
        bookDetailNavGraph(navController = navController)
        loanDetailNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.bookDetailNavGraph(navController: NavHostController) {
    navigation(
        startDestination = "",
        route = Graph.BOOK_DETAILS
    ){

    }
}

fun NavGraphBuilder.loanDetailNavGraph(navController: NavHostController) {
    navigation(
        startDestination = "",
        route = Graph.LOAN_DETAILS
    ){

    }
}


sealed class BottomBarScreen(
    val route: String,
) {
    object Home : BottomBarScreen("bottom_bar_home_screen")
    object Search : BottomBarScreen("bottom_bar_search_screen")
    object Favorite : BottomBarScreen("bottom_bar_favorite_screen")
    object Loan: BottomBarScreen("bottom_bar_loan_screen")
    object Settings: BottomBarScreen("bottom_bar_settings_screen")
}