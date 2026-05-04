package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.ui.theme.*
import com.example.nagarnivedan.ui.components.ProfileItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Profile & Settings") })
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
                .padding(16.dp)
        ) {


            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = DisabledBg),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(BluePrimary.copy(0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = BluePrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            "Virat Jaiswal",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextPrimary
                        )
                        Text(
                            "+91 63885 89752",
                            color = TextSecondary
                        )
                        Text(
                            "Verified",
                            color = StatusResolved,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            Text("Account", color = TextSecondary)

            Spacer(modifier = Modifier.height(8.dp))

            ProfileItem("Edit Profile") {
                navController.navigate("edit_profile")
            }

            ProfileItem("Change Password") {
                navController.navigate("change_password")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔹 AREA SECTION
            Text("Area / Locality", color = TextSecondary)

            Spacer(modifier = Modifier.height(8.dp))

            ProfileItem("Ambedkar Nagar, Ward 12") {
                navController.navigate("change_area")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔥 LOGOUT BUTTON
            Text(
                text = "Log Out",
                color = StatusRejected,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .clickable {
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                    .padding(12.dp)
            )
        }
    }
}