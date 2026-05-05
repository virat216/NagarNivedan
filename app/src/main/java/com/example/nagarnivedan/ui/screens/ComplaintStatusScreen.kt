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
import com.example.nagarnivedan.model.ComplaintDto
import com.example.nagarnivedan.network.RetrofitClient
import com.example.nagarnivedan.ui.components.StatusStep
import com.example.nagarnivedan.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplaintStatusScreen(
    navController: NavController,
    complaintId: String
) {

    var complaint by remember { mutableStateOf<ComplaintDto?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    // 🔥 API CALL
    LaunchedEffect(complaintId) {
        try {
            val response = RetrofitClient.apiService.getMyComplaints()

            if (response.isSuccessful) {
                val list = response.body()?.data ?: emptyList()

                complaint = list.find { it.id == complaintId }

                if (complaint == null) {
                    errorMessage = "Complaint not found"
                }

            } else {
                errorMessage = response.errorBody()?.string() ?: "Error"
            }

        } catch (e: Exception) {
            errorMessage = e.message ?: "Network error"
        }

        isLoading = false
    }

    // 🔄 LOADING
    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    // ❌ ERROR
    if (errorMessage.isNotEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }
        return
    }

    val data = complaint ?: return

    val status = data.status ?: "Registered"

    val steps = listOf(
        "Complaint Registered" to true,
        "Seen by admin" to (status == "In Progress" || status == "Resolved"),
        "Team Assigned / In Progress" to (status == "In Progress" || status == "Resolved"),
        "Complaint Resolved" to (status == "Resolved")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Complaint Status") },
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
                .background(White)
                .padding(16.dp)
        ) {

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = DisabledBg)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = data.title ?: "No Title",
                        color = BluePrimary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Complaint Reference ID: ${data.id}",
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = data.location ?: "No location",
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = data.description ?: "No description",
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider()

                    Spacer(modifier = Modifier.height(12.dp))

                    steps.forEachIndexed { index, (step, completed) ->
                        StatusStep(
                            text = step,
                            completed = completed,
                            isLast = index == steps.lastIndex
                        )
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
                        text = "Last Updated: ${data.updatedAt ?: "-"}",
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}