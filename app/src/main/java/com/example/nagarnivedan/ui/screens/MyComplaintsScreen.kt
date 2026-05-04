package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.data.Complaint
import com.example.nagarnivedan.ui.components.ComplaintItem
import com.example.nagarnivedan.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyComplaintsScreen(navController: NavController) {


    val complaints = listOf(
        Complaint("1", "Water Supply Issue", "Water disrupted in area", "In Progress"),
        Complaint("2", "Road Damage", "Potholes on main road", "Resolved"),
        Complaint("3", "Electricity Issue", "Frequent power cuts", "Registered"),
        Complaint("4", "Garbage Issue", "Garbage not collected", "Rejected")
    )

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

        LazyColumn(contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
        ) {

            items(complaints) { complaint ->
                ComplaintItem(
                    complaint = complaint,
                    onClick = {
                        navController.navigate("complaint_status/${complaint.id}")
                    }
                )
            }
        }
    }
}