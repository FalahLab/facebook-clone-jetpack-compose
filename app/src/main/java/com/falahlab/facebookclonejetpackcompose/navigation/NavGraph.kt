package com.falahlab.facebookclonejetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falahlab.facebookclonejetpackcompose.screens.Add
import com.falahlab.facebookclonejetpackcompose.screens.Friends
import com.falahlab.facebookclonejetpackcompose.screens.Home
import com.falahlab.facebookclonejetpackcompose.screens.Menu
import com.falahlab.facebookclonejetpackcompose.screens.Notification
import com.falahlab.facebookclonejetpackcompose.screens.Profile
import com.falahlab.facebookclonejetpackcompose.screens.Search
import com.falahlab.facebookclonejetpackcompose.screens.Splash
import com.falahlab.facebookclonejetpackcompose.screens.Nav
import com.falahlab.facebookclonejetpackcompose.screens.Videos


@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash.routes,
        modifier = modifier
    ) {
        composable(Routes.Splash.routes) { Splash(navController) }
        composable(Routes.Home.routes) { Home() }
        composable(Routes.Search.routes) { Search() }
        composable(Routes.Add.routes) { Add() }
        composable(Routes.Notification.routes) { Notification() }
        composable(Routes.Profile.routes) { Profile() }
        composable(Routes.Nav.routes) { Nav(navController) }
        composable(Routes.Videos.routes) { Videos() }
        composable(Routes.Friends.routes) { Friends() }
        composable(Routes.Menu.routes) { Menu() }



    }
}