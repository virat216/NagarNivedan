package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.sp
import com.example.nagarnivedan.ui.components.StepItem
import com.example.nagarnivedan.ui.theme.*
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterComplaintScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Register a New Complaint") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            {
                Button(
                    onClick = {
                        navController.navigate("category")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Start Complaint")
                }
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
                text = buildAnnotatedString {
                    append("Submit a civic complaint in a few simple steps.\n")
                    append("Below are the steps to register a complaint. Click on")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(" Start Complaint ")
                    }
                    append("to begin.")
                },
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))


            StepItem("Choose category", Icons.Default.List)
            StepItem("Describe the issue", Icons.Default.Edit)
            StepItem("Add location", Icons.Default.LocationOn)
            StepItem("Add photos (optional)", Icons.Default.PhotoCamera)
            StepItem("Submit", Icons.Default.CheckCircle)

            Spacer(modifier = Modifier.height(20.dp))


            Card(
                colors = CardDefaults.cardColors(containerColor = DisabledBg),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Your complaint is linked to your verified account. You may hide your identity from field staff.",
                    modifier = Modifier.padding(12.dp),
                    color = TextSecondary
                )
            }
        }
    }
}