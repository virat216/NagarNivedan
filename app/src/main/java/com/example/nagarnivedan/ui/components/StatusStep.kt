package com.example.nagarnivedan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nagarnivedan.navigation.AppNavigation
import com.example.nagarnivedan.ui.theme.*

@Composable
fun StatusStep(
    text: String,
    completed: Boolean,
    isLast: Boolean
) {
    Row (modifier = Modifier.fillMaxWidth()){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = if (completed)
                    Icons.Default.CheckCircle
                else
                    Icons.Outlined.RadioButtonUnchecked,
                contentDescription = null,
                tint = if (completed) BluePrimary else Border
            )
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(24.dp)
                        .background(
                            if (completed) BluePrimary else Border.copy(alpha = 0.5f)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            color = if (completed) TextPrimary else TextSecondary
        )
    }
}