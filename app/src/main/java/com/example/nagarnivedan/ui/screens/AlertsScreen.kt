package com.example.nagarnivedan.ui.screens

import android.util.Log
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.model.AlertDto
import com.example.nagarnivedan.network.RetrofitClient
import com.example.nagarnivedan.ui.components.AlertItem
import com.example.nagarnivedan.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertsScreen(navController: NavController) {

    val scope = rememberCoroutineScope()

    var alerts by remember { mutableStateOf<List<AlertDto>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    // 🔥 API CALL
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = RetrofitClient.apiService.getAlerts()

                if (response.isSuccessful) {
                    alerts = response.body()?.data ?: emptyList()
                } else {
                    errorMessage = response.errorBody()?.string() ?: "Error fetching alerts"
                }

            } catch (e: Exception) {
                errorMessage = e.message ?: "Network error"
                Log.d("ALERTS", "Exception: ${e.message}")
            }

            isLoading = false
        }
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
        ) {

            // 🔄 LOADING
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                return@Column
            }

            // ❌ ERROR
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {

                // 🔹 HEADER CARD (unchanged UI)
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
                        }
                    }
                }

                // 🔥 REAL DATA
                items(alerts) { alert ->

                    // Convert AlertDto → UI Alert model
                    val uiAlert = com.example.nagarnivedan.data.Alert(
                        id = alert.id ?: "",
                        title = alert.title ?: "No Title",
                        location = "",
                        time = alert.date ?: "",
                        description = alert.message ?: "",
                        category = "General",
                        isRead = false,
                        type = "info"
                    )

                    AlertItem(
                        alert = uiAlert,
                        onClick = {
                            navController.navigate("alert_detail/${alert.id}")
                        }
                    )
                }
            }
        }
    }
}