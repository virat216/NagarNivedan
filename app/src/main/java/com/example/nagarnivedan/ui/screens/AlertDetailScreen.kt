package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.data.Alert
import com.example.nagarnivedan.ui.theme.*
import com.example.nagarnivedan.ui.components.AdvisoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDetailScreen(navController: NavController, alertId: String) {

    val alert = Alert(
        alertId,
        "Water Supply Interruption",
        "Ward 14, Block 18, Ambedkar Nagar, Agra",
        "18th March, 12:00 PM to 6:00 PM",
        "Water supply will be interrupted due to urgent demand caused by fire accident.",
        "Water"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Alerts") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {


            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = alert.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = BluePrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        Icon(Icons.Default.LocationOn, null, tint = BluePrimary)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(alert.location, color = TextSecondary)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {
                        Icon(Icons.Default.CalendarMonth, null, tint = BluePrimary)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(alert.time, color = TextSecondary)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = alert.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Details & Advisory",
                style = MaterialTheme.typography.titleMedium,
                color = BluePrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Advisory Cards
            AdvisoryCard("Expect no water supply during this period.")
            AdvisoryCard("Store water in advance for your needs.")
        }
    }
}