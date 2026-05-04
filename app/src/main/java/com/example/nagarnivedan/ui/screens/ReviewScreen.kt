package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.data.Complaint
import com.example.nagarnivedan.data.ComplaintDraft
import com.example.nagarnivedan.data.ComplaintRepository
import com.example.nagarnivedan.ui.theme.*
import com.example.nagarnivedan.ui.components.ReviewCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(navController: NavController) {

    val category = ComplaintDraft.category ?: "Not selected"
    val description = ComplaintDraft.description ?: "No description"
    val location = ComplaintDraft.location ?: "Location not added"
    val photo = if (ComplaintDraft.photoAdded) "1 photo added" else "No photos"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Review Complaint") },
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
                    ComplaintRepository.addComplaint(
                        Complaint(
                            id = System.currentTimeMillis().toString(),
                            title = category,
                            description = description,
                            status = "Registered"
                        )
                    )
                    ComplaintDraft.reset()
                    navController.navigate("success")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()   // 👈 FIX overlap
                    .padding(16.dp)
            ) {
                Text("Submit Complaint")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
                .padding(16.dp)
        ) {


            Text(
                text = "Please review your complaint before submitting. You can go back to edit any detail if required.",
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(16.dp))


            ReviewCard("Category", category, "Edit"){
                navController.navigate("category")
            }

            Spacer(modifier = Modifier.height(12.dp))


            ReviewCard("Description", description, "View & Edit"){
                navController.navigate("description")
            }

            Spacer(modifier = Modifier.height(12.dp))


            ReviewCard("Location", location, "Edit"){
                navController.navigate("location")
            }

            Spacer(modifier = Modifier.height(12.dp))


            ReviewCard("Photos", photo, "View & Edit"){
                navController.navigate("photos")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Divider()

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Once submitted, the complaint cannot be edited.\nFalse complaints may lead to action.",
                color = TextSecondary
            )
        }
    }
}