package com.falahlab.facebookclonejetpackcompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
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

@Composable
fun BottomNavBar(navController1: NavHostController) {
    val items = listOf(
        NavItem("Home", Routes.Home.routes, Icons.Rounded.Home),
        NavItem("Search", Routes.Search.routes, Icons.Rounded.Search),
        NavItem("Add", Routes.Add.routes, Icons.Rounded.Add),
        NavItem("Notification", Routes.Notification.routes, Icons.Rounded.Notifications),
        NavItem("Profile", Routes.Profile.routes, Icons.Rounded.Person)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController1: NavHostController) {
    val items = listOf(
        NavItem("Videos", Routes.Videos.routes, Icons.Rounded.PlayArrow),
        NavItem("Friends", Routes.Friends.routes, Icons.Rounded.Person),
        NavItem("Menu", Routes.Menu.routes, Icons.Rounded.Menu)
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
