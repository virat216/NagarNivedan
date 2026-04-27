package com.example.nagarnivedan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.nagarnivedan.ui.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(navController)
        }

        composable("register_complaint") {
            // empty for now
        }

        composable("track_complaint") {
            // empty for now
        }

        composable("complaints") { }
        composable("alerts") { }
        composable("profile") { }
    }
}