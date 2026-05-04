package com.example.nagarnivedan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.data.ComplaintDraft
import com.example.nagarnivedan.ui.theme.*
import com.example.nagarnivedan.ui.components.LocationField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(navController: NavController) {

    // 🔹 Fields
    var street by remember { mutableStateOf("") }
    var locality by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var ward by remember { mutableStateOf("") } // optional
    var district by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }

    // 🔹 Validation
    val isValid = street.isNotBlank() &&
            locality.isNotBlank() &&
            city.isNotBlank() &&
            district.isNotBlank() &&
            state.isNotBlank() &&
            pincode.isNotBlank()
    val scope = rememberCoroutineScope()

    fun fetchLocationFromPincode(pincode: String) {
        scope.launch {
            try {
                val response = java.net.URL("https://api.postalpincode.in/pincode/$pincode")
                    .readText()

                val jsonArray = org.json.JSONArray(response)
                val postOffice = jsonArray.getJSONObject(0)
                    .getJSONArray("PostOffice")
                    .getJSONObject(0)

                district = postOffice.getString("District")
                state = postOffice.getString("State")
                city = postOffice.getString("Region")

            } catch (e: Exception) {

            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Location") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        },

        bottomBar = {
            Button(
                onClick = {
                    val fullAddress = buildString {
                        append(street)
                        append(", $locality")
                        if (ward.isNotBlank()) append(", Ward $ward")
                        append(", $city")
                        append(", $district")
                        append(", $state - $pincode")
                    }

                    ComplaintDraft.location = fullAddress
                    navController.navigate("photos")
                },
                enabled = isValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp)
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {


            Text(
                text = "Add Location Details",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Provide complete address for accurate complaint tracking",
                color = TextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))


            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    LocationField(
                        label = "Street",
                        value = street,
                        onChange = { street = it }
                    )

                    LocationField(
                        label = "Locality",
                        value = locality,
                        onChange = { locality = it }
                    )

                    LocationField(
                        label = "City",
                        value = city,
                        onChange = { city = it }
                    )

                    LocationField(
                        label = "Ward",
                        value = ward,
                        onChange = { ward = it },
                        isRequired = false
                    )

                    LocationField(
                        label = "District",
                        value = district,
                        onChange = { district = it }
                    )

                    LocationField(
                        label = "State",
                        value = state,
                        onChange = { state = it }
                    )

                    LocationField(
                        label = "Pincode",
                        value = pincode,
                        onChange = {
                            pincode = it
                            if (it.length == 6) {
                                fetchLocationFromPincode(it)
                            }
                        },
                        isPincode = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                    if (isValid) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = StatusResolved.copy(alpha = 0.1f)
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("📍", fontSize = MaterialTheme.typography.bodyLarge.fontSize)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Location added successfully",
                                    color = StatusResolved,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}