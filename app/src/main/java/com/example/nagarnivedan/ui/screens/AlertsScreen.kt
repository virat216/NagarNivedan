package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.data.Alert
import com.example.nagarnivedan.ui.components.AlertItem
import com.example.nagarnivedan.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertsScreen(navController: NavController) {

    val alerts = remember {
        mutableStateListOf(
            Alert("1", "Water Supply Interruption", "Ward 5", "Now", "", "Water", false),
            Alert("2", "Road Closure", "Ward 5", "2h", "", "Road", true),
            Alert("3", "Power Outage", "Ward 5", "2h", "", "Electricity", false),
            Alert("4", "Fog Alert", "Ward 5", "2h", "", "Weather", true)
        )
    }
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {


            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = DisabledBg)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    BluePrimary.copy(alpha = 0.15f),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = null,
                                tint = BluePrimary
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {

                            Text(
                                text = "Stay updated!",
                                style = MaterialTheme.typography.titleMedium,
                                color = TextPrimary
                            )

                            Text(
                                text = "Important alerts and notifications from your city.",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary
                            )
                        }


                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(StatusRejected, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "1",
                                color = White,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }


            items(alerts) { alert ->
                AlertItem(
                    alert = alert,
                    onClick =
                        {
                            alert.isRead = true
                            navController.navigate("alert_detail/${alert.id}")
                        }
                )
            }
        }

    }
}