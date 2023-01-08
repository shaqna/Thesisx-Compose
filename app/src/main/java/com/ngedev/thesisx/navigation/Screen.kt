package com.ngedev.thesisx.navigation

sealed class Screen(route: String) {
    object Login: Screen("login_screen")
    object Register: Screen("register_screen")
}