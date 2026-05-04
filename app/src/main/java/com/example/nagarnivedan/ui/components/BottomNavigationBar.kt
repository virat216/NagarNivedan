package com.example.nagarnivedan.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.nagarnivedan.ui.theme.White

@Composable
fun BottomNavigationBar(navController: NavController) {

    val currentRoute = navController.currentBackStackEntry?.destination?.route

    NavigationBar(containerColor = White) {

        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo("home")
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = currentRoute == "complaints",
            onClick = {
                navController.navigate("complaints") {
                    popUpTo("home")
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text("Complaints") }
        )

        NavigationBarItem(
            selected = currentRoute == "alerts",
            onClick = {
                navController.navigate("alerts") {
                    popUpTo("home")
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
            label = { Text("Alerts") }
        )

        NavigationBarItem(
            selected = currentRoute == "profile",
            onClick = {
                navController.navigate("profile") {
                    popUpTo("home")
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Profile") }
        )
    }
}