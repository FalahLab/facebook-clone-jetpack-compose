package com.falahlab.facebookclonejetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falahlab.facebookclonejetpackcompose.screens.Add
import com.falahlab.facebookclonejetpackcompose.screens.Home
import com.falahlab.facebookclonejetpackcompose.screens.Notification
import com.falahlab.facebookclonejetpackcompose.screens.Search
import com.falahlab.facebookclonejetpackcompose.screens.Splash


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Splash.route) {


        composable(Routes.Splash.route) {
            Splash()


        }
        composable(Routes.Home.route) {
            Home()
        }
        composable(Routes.Search.route) {
            Search()
        }
        composable(Routes.Add.route) {
            Add()
        }
        composable(Routes.Notification.route) {
            Notification()

        }
    }
}