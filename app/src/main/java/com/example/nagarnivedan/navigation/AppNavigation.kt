package com.example.nagarnivedan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.nagarnivedan.ui.screens.HomeScreen
import com.example.nagarnivedan.ui.screens.RegisterComplaintScreen
import com.example.nagarnivedan.ui.screens.CategoryScreen
import com.example.nagarnivedan.ui.screens.DescriptionScreen
import com.example.nagarnivedan.ui.screens.LocationScreen
import com.example.nagarnivedan.ui.screens.PhotoScreen
import com.example.nagarnivedan.ui.screens.ReviewScreen
import com.example.nagarnivedan.ui.screens.SuccessScreen
import com.example.nagarnivedan.ui.screens.OtherIssueScreen
import com.example.nagarnivedan.ui.screens.TrackComplaintScreen
import com.example.nagarnivedan.ui.screens.ComplaintStatusScreen
import com.example.nagarnivedan.ui.screens.MyComplaintsScreen
import com.example.nagarnivedan.ui.screens.AlertsScreen
import com.example.nagarnivedan.ui.screens.AlertDetailScreen
import com.example.nagarnivedan.ui.screens.ProfileScreen
import com.example.nagarnivedan.ui.screens.EditProfileScreen
import com.example.nagarnivedan.ui.screens.ChangePasswordScreen
import com.example.nagarnivedan.ui.screens.ChangeAreaScreen




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
        composable("complaints") {
            MyComplaintsScreen(navController)
        }
        composable("track_complaint") {
            TrackComplaintScreen(navController)
        }
        composable("complaint_status/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?:""
            ComplaintStatusScreen(navController, id)
        }
        composable("alerts") {
            AlertsScreen(navController)
        }

        composable("alert_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            AlertDetailScreen(navController, id)
        }
        composable("profile") { ProfileScreen(navController) }
        composable("edit_profile") { EditProfileScreen(navController) }
        composable("change_password") { ChangePasswordScreen(navController) }
        composable("register_complaint")
        {
            RegisterComplaintScreen(navController)
        }
        composable("category") {
            CategoryScreen(navController)
        }
        composable("description")
        {
            DescriptionScreen(navController)
        }
        composable("location") {
            LocationScreen(navController)
        }
        composable("photos") {
            PhotoScreen(navController)
        }
        composable("review") {
            ReviewScreen(navController)
        }
        composable("change_area") {
            ChangeAreaScreen(navController) }
        composable("success") {
            SuccessScreen(navController)
        }
        composable("other_issue") {
            OtherIssueScreen(navController)
        }
    }
}