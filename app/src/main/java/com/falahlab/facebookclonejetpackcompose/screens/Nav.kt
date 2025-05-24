package com.falahlab.facebookclonejetpackcompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.falahlab.facebookclonejetpackcompose.model.NavItem
import com.falahlab.facebookclonejetpackcompose.navigation.Routes


@Composable
fun Nav(navController: NavHostController) {
    val navController1 = rememberNavController()

    Scaffold(
        topBar = { TopNavBar(navController1) },
        bottomBar = { BottomNavBar(navController1) }
    ) { innerPadding ->
        NavHost(
            navController = navController1,
            startDestination = Routes.Home.routes,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Bottom navigation screens
            composable(Routes.Home.routes) { Home() }
            composable(Routes.Search.routes) { Search() }
            composable(Routes.Add.routes) { Add() }
            composable(Routes.Notification.routes) { Notification() }
            composable(Routes.Profile.routes) { Profile() }

            // Top navigation screens
            composable(Routes.Videos.routes) { Videos() }
            composable(Routes.Friends.routes) { Friends() }
            composable(Routes.Menu.routes) { Menu() }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController1: NavHostController) {
    val items = listOf(
        NavItem("Videos", Routes.Videos.routes, Icons.Rounded.PlayArrow),
        NavItem("Friends", Routes.Friends.routes, Icons.Rounded.Person),
        NavItem("Menu", Routes.Menu.routes, Icons.Rounded.Menu),
        NavItem("Profile", Routes.Profile.routes, Icons.Rounded.AccountCircle),
        NavItem("Market", Routes.Market.routes, Icons.Rounded.ShoppingCart),
        NavItem("Search", Routes.Search.routes, Icons.Rounded.Search),
        NavItem("Add", Routes.Add.routes, Icons.Rounded.Add),
        NavItem("Notification", Routes.Notification.routes, Icons.Rounded.Notifications),
    )
    val backStackEntry by navController1.currentBackStackEntryAsState()

    CenterAlignedTopAppBar(
        title = { Text("Facebook Clone") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items.forEach {
                    val selected = it.route == backStackEntry?.destination?.route

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                navController1.navigate(it.route) {
                                    popUpTo(navController1.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            .padding(vertical = 4.dp) // Minimal vertical space
                    ) {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.title,
                            tint = if (selected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (selected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    )
}




@Composable
fun BottomNavBar(navController1: NavHostController) {
    val items = listOf(
        NavItem("Home", Routes.Home.routes, Icons.Rounded.Home),


    )
    val backStackEntry by navController1.currentBackStackEntryAsState()

    BottomAppBar {
        items.forEach {
            val selected = it.route == backStackEntry?.destination?.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController1.navigate(it.route) {
                        popUpTo(navController1.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(it.icon, contentDescription = it.title) },
                label = { Text(it.title) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NavPreview() {
    val previewNavController = rememberNavController()
    Nav(navController = previewNavController)
}