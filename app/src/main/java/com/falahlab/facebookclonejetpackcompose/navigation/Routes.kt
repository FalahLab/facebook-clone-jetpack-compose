package com.falahlab.facebookclonejetpackcompose.navigation

sealed class Routes(val route: String) {

    object Home : Routes("home")
    object Search : Routes("search")
    object Add : Routes("add")
    object Notification : Routes("notification")
    object Profile : Routes("profile")
    object Splash : Routes("splash")
}
