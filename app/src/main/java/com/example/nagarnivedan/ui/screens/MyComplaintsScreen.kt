package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.ui.components.ComplaintItem
import com.example.nagarnivedan.ui.theme.White
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.example.nagarnivedan.network.RetrofitClient
import com.example.nagarnivedan.model.ComplaintDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyComplaintsScreen(navController: NavController) {

    var complaints by remember { mutableStateOf<List<ComplaintDto>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    // 🔥 API call when screen opens
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = RetrofitClient.apiService.getMyComplaints()

                if (response.isSuccessful) {
                    complaints = response.body()?.data ?: emptyList()
                } else {
                    errorMessage = response.errorBody()?.string() ?: "Error fetching data"
                }

            } catch (e: Exception) {
                errorMessage = e.message ?: "Network error"
            }

            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Complaints") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->

        // 🔄 LOADING STATE
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        // ❌ ERROR STATE
        if (errorMessage.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: $errorMessage")
            }
            return@Scaffold
        }

        // 📭 EMPTY STATE
        if (complaints.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No complaints found")
            }
            return@Scaffold
        }

        // ✅ DATA LIST
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
        ) {
            items(complaints) { complaint ->

                ComplaintItem(
                    complaint = complaint,   // ⚠️ must support nullable fields
                    onClick = {
                        navController.navigate("complaint_status/${complaint.id ?: ""}")
                    }
                )
            }
        }
    }
}