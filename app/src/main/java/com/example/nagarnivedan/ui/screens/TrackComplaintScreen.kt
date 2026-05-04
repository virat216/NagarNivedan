package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackComplaintScreen(navController: NavController) {

    var referenceId by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Track Complaint") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    navController.navigate("complaint_status/$referenceId")
                },
                enabled = referenceId.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Track Complaint",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))


            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Enter the complaint reference ID to check its current status.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    OutlinedTextField(
                        value = referenceId,
                        onValueChange = { referenceId = it },
                        placeholder = {
                            Text(
                                "Enter reference ID",
                                color = TextSecondary.copy(alpha = 0.7f)
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp), // same shape for both
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = TextPrimary // 👈 FIXED TEXT VISIBILITY
                        ),
                        trailingIcon = {
                            TextButton(
                                onClick = {
                                    // TODO: paste from clipboard later
                                }
                            ) {
                                Text(
                                    "Paste",
                                    color = BluePrimary,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BluePrimary,
                            unfocusedBorderColor = Border.copy(alpha = 0.7f),
                            cursorColor = BluePrimary
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Example: ABC123456",
                        color = TextSecondary.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}