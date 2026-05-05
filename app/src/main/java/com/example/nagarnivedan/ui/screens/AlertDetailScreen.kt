package com.example.nagarnivedan.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.model.AlertDto
import com.example.nagarnivedan.network.RetrofitClient
import com.example.nagarnivedan.ui.theme.*
import com.example.nagarnivedan.ui.components.AdvisoryCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDetailScreen(navController: NavController, alertId: String) {

    val scope = rememberCoroutineScope()

    var alert by remember { mutableStateOf<AlertDto?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    // 🔥 API CALL
    LaunchedEffect(alertId) {
        scope.launch {
            try {
                val response = RetrofitClient.apiService.getAlertById(alertId)

                if (response.isSuccessful) {
                    alert = response.body()
                } else {
                    errorMessage = response.errorBody()?.string() ?: "Error fetching alert"
                }

            } catch (e: Exception) {
                errorMessage = e.message ?: "Network error"
                Log.d("ALERT_DETAIL", "Exception: ${e.message}")
            }

            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Alert Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        // 🔄 LOADING
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        // ❌ ERROR
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
            return@Scaffold
        }

        // ⚠️ SAFETY
        val data = alert ?: return@Scaffold

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .background(White)
        ) {

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = data.title ?: "No Title",
                        style = MaterialTheme.typography.titleLarge,
                        color = BluePrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        Icon(Icons.Default.LocationOn, null, tint = BluePrimary)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(data.message ?: "Location not available", color = TextSecondary)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {
                        Icon(Icons.Default.CalendarMonth, null, tint = BluePrimary)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(data.date ?: "No date", color = TextSecondary)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = data.message ?: "No description",
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

            // 🔹 Static advisory (can be dynamic later)
            AdvisoryCard("Follow instructions provided by local authorities.")
            AdvisoryCard("Stay updated via official notifications.")
        }
    }
}