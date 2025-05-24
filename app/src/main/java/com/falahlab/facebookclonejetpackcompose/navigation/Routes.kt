package com.falahlab.facebookclonejetpackcompose.navigation

sealed class Routes(val routes: String) {

    object Home : Routes("home")
    object Search : Routes("search")
    object Add : Routes("add")
    object Notification : Routes("notification")
    object Profile : Routes("profile")
    object Splash : Routes("splash")
    object Videos : Routes("videos")
    object Friends : Routes("friends")
    object Menu : Routes("menu")
    object Nav : Routes("Nav")
}
