package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.ui.theme.*
import com.example.nagarnivedan.ui.components.StatusStep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplaintStatusScreen(
    navController: NavController,
    complaintId: String
) {


    val title = "Water Supply Issue"
    val id= complaintId
    val location = "Ward 14, Block 18, Ambedkar Nagar, Agra"
    val description = "Water supply is completely disrupted for three days."
    val lastUpdated = "12 Jan, 11:40 AM"

    val steps = listOf(
        "Complaint Registered" to true,
        "Seen by admin" to true,
        "Team Assigned / In Progress" to true,
        "Complaint Resolved" to false
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Complaint Status") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
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
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = DisabledBg)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {


                    Text(
                        text = title,
                        color = BluePrimary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Complaint Refrence ID: $id",
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(6.dp))


                    Text(
                        text = location,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Text(
                        text = description,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider()

                    Spacer(modifier = Modifier.height(12.dp))


                    steps.forEachIndexed { index,(step, completed) ->
                        StatusStep( text= step,
                            completed= completed,
                            isLast = index == steps.lastIndex)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Resolution timelines may vary depending upon the issue.",
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Last Updated: $lastUpdated",
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}