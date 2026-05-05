package com.example.nagarnivedan.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.model.UserDto
import com.example.nagarnivedan.network.RetrofitClient
import com.example.nagarnivedan.data.SessionManager
import com.example.nagarnivedan.ui.components.ProfileItem
import com.example.nagarnivedan.ui.theme.*
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {

    val context = LocalContext.current
    val session = SessionManager(context)
    val scope = rememberCoroutineScope()

    var user by remember { mutableStateOf<UserDto?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    // 🔥 FETCH PROFILE
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = RetrofitClient.apiService.getProfile()

                if (response.isSuccessful) {
                    user = response.body()?.data
                } else {
                    errorMessage = response.errorBody()?.string() ?: "Error fetching profile"
                }

            } catch (e: Exception) {
                errorMessage = e.message ?: "Network error"
                Log.d("PROFILE", "Exception: ${e.message}")
            }

            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Profile & Settings") })
        }
    ) { padding ->

        // 🔄 LOADING
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
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

        val data = user

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
                .padding(16.dp)
        ) {

            // 🔹 USER CARD
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = DisabledBg),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(BluePrimary.copy(0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = BluePrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            data?.username ?: "User",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextPrimary
                        )

                        Text(
                            data?.email ?: "",
                            color = TextSecondary
                        )

                        Text(
                            "Verified",
                            color = StatusResolved,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔹 ACCOUNT
            Text("Account", color = TextSecondary)

            Spacer(modifier = Modifier.height(8.dp))

            ProfileItem("Edit Profile") {
                navController.navigate("edit_profile")
            }

            ProfileItem("Change Password") {
                navController.navigate("change_password")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔹 AREA
            Text("Area / Locality", color = TextSecondary)

            Spacer(modifier = Modifier.height(8.dp))

            ProfileItem("Ambedkar Nagar, Ward 12") {
                navController.navigate("change_area")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔥 LOGOUT
            Text(
                text = "Log Out",
                color = StatusRejected,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .clickable {
                        // 🔥 CLEAR SESSION + COOKIES
                        RetrofitClient.clearSession()
                        session.logout()

                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                    .padding(12.dp)
            )
        }
    }
}