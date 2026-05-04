package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nagarnivedan.ui.components.CategoryItem
import androidx.navigation.NavController
import com.example.nagarnivedan.ui.theme.BluePrimary
import com.example.nagarnivedan.ui.theme.TextSecondary
import com.example.nagarnivedan.ui.theme.White
import com.example.nagarnivedan.data.ComplaintDraft
import com.example.nagarnivedan.ui.theme.Border


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController) {

    var selectedCategory by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Complaint Category") },
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
                    ComplaintDraft.category = selectedCategory
                    navController.navigate("description")
                },
                enabled = selectedCategory != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Continue")
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
                text = "Please select the category that best describes your complaint",
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(20.dp))


            Column {

                Row(modifier = Modifier.fillMaxWidth()) {
                    CategoryItem(
                        title = "Garbage & Sanitation",
                        icon = Icons.Default.Delete,
                        isSelected = selectedCategory == "Garbage",
                        onClick = { selectedCategory = "Garbage" },
                        modifier = Modifier.weight(1f)
                    )

                    CategoryItem(
                        title = "Water Supply Issue",
                        icon = Icons.Default.WaterDrop,
                        isSelected = selectedCategory == "Water",
                        onClick = { selectedCategory = "Water" },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    CategoryItem(
                        title = "Electricity Issue",
                        icon = Icons.Default.Bolt,
                        isSelected = selectedCategory == "Electricity",
                        onClick = { selectedCategory = "Electricity" },
                        modifier = Modifier.weight(1f)
                    )

                    CategoryItem(
                        title = "Road Damage",
                        icon = Icons.Default.Build,
                        isSelected = selectedCategory == "Road",
                        onClick = { selectedCategory = "Road" },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))


                OutlinedButton(
                    onClick = { navController.navigate("other_issue") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = BluePrimary
                    ),
                    border = BorderStroke(1.dp, Border.copy(alpha = 0.8f))
                ) {
                    Text("Other Issue")
                }
            }
        }
    }
}