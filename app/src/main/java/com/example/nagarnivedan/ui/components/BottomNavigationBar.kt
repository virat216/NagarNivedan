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

    NavigationBar(
        containerColor = White
    ) {

        NavigationBarItem(
            selected = true,
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("complaints") },
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text("Complaints") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("alerts") },
            icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
            label = { Text("Alerts") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("profile") },
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Profile") }
        )
    }
}