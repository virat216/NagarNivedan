package com.example.nagarnivedan.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.nagarnivedan.ui.theme.*

@Composable
fun LocationField(
    label: String,
    value: String,
    onChange: (String) -> Unit,
    isRequired: Boolean = true,
    isPincode: Boolean = false
) {
    var isTouched by remember { mutableStateOf(false) }
    // 🔹 Validation
    val isError = isTouched && (
            (isRequired && value.isBlank()) ||
                    (isPincode && value.length in 1..5)
            )
    OutlinedTextField(
        value = value,
        onValueChange = { input ->

            isTouched = true

            if (isPincode) {
                val filtered = input.filter { it.isDigit() }.take(6)
                onChange(filtered)
            } else {
                onChange(input)
            }
        },

        label = {
            Text(if (isRequired) "$label *" else label)
        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),

        singleLine = true,

        isError = isError,

        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPincode) KeyboardType.Number else KeyboardType.Text
        ),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BluePrimary,
            unfocusedBorderColor = Border,
            focusedLabelColor = BluePrimary,
            unfocusedLabelColor = TextSecondary,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            cursorColor = BluePrimary,
            errorBorderColor = StatusRejected
        )
    )


    if (isError) {
        val errorText = when {
            value.isBlank() -> "$label is required"
            isPincode -> "Pincode must be 6 digits"
            else -> ""
        }

        Text(
            text = errorText,
            color = StatusRejected,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
    }
}