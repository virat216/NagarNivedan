package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.ui.components.PrimaryCardButton
import com.example.nagarnivedan.ui.components.SecondaryCardButton
import com.example.nagarnivedan.ui.components.BottomNavigationBar
import com.example.nagarnivedan.ui.theme.DisabledBg
import com.example.nagarnivedan.ui.theme.TextPrimary
import com.example.nagarnivedan.ui.theme.TextSecondary
import com.example.nagarnivedan.ui.theme.White
import androidx.compose.material3.MaterialTheme

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(DisabledBg)
        ) {

            // 🔹 Header Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "NagarNivedan",
                    style = MaterialTheme.typography.headlineLarge,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Agra Road, Bhooda ▼",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔹 Primary Card (Register Complaint)
            PrimaryCardButton(
                title = "Register New Complaint",
                subtitle = "Report civic issues in your area",
                icon = Icons.Default.Add,
                onClick = {
                    navController.navigate("register_complaint")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Secondary Card (Track Status)
            SecondaryCardButton(
                title = "Track Complaint Status",
                subtitle = "View status updates for complaints",
                icon = Icons.Default.Search,
                onClick = {
                    navController.navigate("track_complaint")
                }
            )
        }
    }
}