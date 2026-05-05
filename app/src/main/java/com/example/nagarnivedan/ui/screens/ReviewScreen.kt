package com.example.nagarnivedan.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.nagarnivedan.data.ComplaintDraft
import com.example.nagarnivedan.network.RetrofitClient
import com.example.nagarnivedan.ui.theme.*
import com.example.nagarnivedan.ui.components.ReviewCard
import com.example.nagarnivedan.utils.uriToFile
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import okhttp3.RequestBody
import okhttp3.MultipartBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody.Companion.asRequestBody

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(navController: NavController) {

    val context = LocalContext.current

    val category = ComplaintDraft.category ?: ""
    val description = ComplaintDraft.description ?: ""
    val location = ComplaintDraft.location ?: "Location not added"
    val photoCount = ComplaintDraft.imageUris.size

    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

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
                    scope.launch {
                        isLoading = true
                        errorMessage = ""

                        try {
                            // 🔥 CATEGORY MAPPING
                            val backendCategory = when (category.lowercase()) {
                                "garbage" -> "garbage_sanitation"
                                "water" -> "water_supply"
                                "road" -> "road_damage"
                                "electricity" -> "streetlight"
                                else -> "other"
                            }

                            // 🔥 VALIDATION
                            if (description.length < 10) {
                                errorMessage = "Description must be at least 10 characters"
                                isLoading = false
                                return@launch
                            }

                            // 🔥 IMAGE → MULTIPART
                            val imageParts = ComplaintDraft.imageUris.map { uri ->
                                val file = uriToFile(context, uri)
                                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                                MultipartBody.Part.createFormData("photos", file.name, requestFile)
                            }

                            val response = RetrofitClient.apiService.createComplaint(
                                category = backendCategory.toRequestBodyText(),
                                description = description.toRequestBodyText(),
                                location = location.toRequestBodyText(),
                                photos = imageParts
                            )

                            if (response.isSuccessful) {
                                Log.d("CREATE", "Success")
                                ComplaintDraft.reset()
                                navController.navigate("success")
                            } else {
                                val error = response.errorBody()?.string()
                                Log.d("CREATE_FULL", "Error: $error")
                                errorMessage = error ?: "Failed to submit"
                            }

                        } catch (e: Exception) {
                            Log.d("CREATE", "Exception: ${e.message}")
                            errorMessage = e.message ?: "Network error"
                        }

                        isLoading = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp)
            ) {
                Text(if (isLoading) "Submitting..." else "Submit Complaint")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Please review your complaint before submitting.",
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            ReviewCard("Category", category, "Edit") {
                navController.navigate("category")
            }

            Spacer(modifier = Modifier.height(12.dp))

            ReviewCard("Description", description, "Edit") {
                navController.navigate("description")
            }

            Spacer(modifier = Modifier.height(12.dp))

            ReviewCard("Location", location, "Edit") {
                navController.navigate("location")
            }

            Spacer(modifier = Modifier.height(12.dp))

            ReviewCard(
                "Photos",
                if (photoCount > 0) "$photoCount photo(s) added" else "No photos",
                "Edit"
            ) {
                navController.navigate("photos")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 🔴 ERROR MESSAGE
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Divider()

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Once submitted, the complaint cannot be edited.",
                color = TextSecondary
            )
        }
    }
}

/* 🔧 Helper */
fun String.toRequestBodyText(): RequestBody {
    return this.toRequestBody("text/plain".toMediaTypeOrNull())
}