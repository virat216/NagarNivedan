package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

import com.example.nagarnivedan.data.ComplaintDraft
import com.example.nagarnivedan.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherIssueScreen(navController: NavController) {

    var customIssue by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Other Issue") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },

        bottomBar = {
            Button(
                onClick = {
                    ComplaintDraft.category = customIssue
                    navController.navigate("description")
                },
                enabled = customIssue.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()   // 👈 prevents overlap
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Continue",
                    fontWeight = FontWeight.SemiBold
                )
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
                text = "Please describe your issue",
                color = TextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))


            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = DisabledBg)
            ) {
                BasicTextField(
                    value = customIssue,
                    onValueChange = { customIssue = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(12.dp),
                    decorationBox = { innerTextField ->
                        if (customIssue.isEmpty()) {
                            Text(
                                text = "Enter your issue here...",
                                color = TextSecondary
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }
    }
}