package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.ui.theme.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.nagarnivedan.data.ComplaintDraft


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionScreen(navController: NavController) {

    var description by remember { mutableStateOf("") }
    val maxWords = 50

    val wordCount = description.trim()
        .split("\\s+".toRegex())
        .filter { it.isNotEmpty() }
        .size

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Describe the Issue") },
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
                    ComplaintDraft.description = description
                    navController.navigate("location")
                },
                enabled = description.isNotBlank(),
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
                text = "Provide a clear and concise description of the issue",
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = DisabledBg)
            ) {
                BasicTextField(
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(12.dp),
                    decorationBox = { innerTextField ->
                        if (description.isEmpty()) {
                            Text(
                                text = "e.g., \"What is the issue? Where is it happening? Since when has it been occurring?. Include any important details that can help resolve the issue.\"",
                                color = TextSecondary
                            )
                        }
                        innerTextField()
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "$wordCount/$maxWords words",
                    color = TextSecondary
                )
            }
        }
    }
}