package com.example.nagarnivedan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nagarnivedan.ui.theme.*

@Composable
fun StatusBadge(status: String) {

    val (bgColor, textColor) = when (status) {
        "Registered" -> StatusRegistered.copy(alpha = 0.15f) to StatusRegistered
        "In Progress" -> StatusInProgress.copy(alpha = 0.15f) to StatusInProgress
        "Resolved" -> StatusResolved.copy(alpha = 0.15f) to StatusResolved
        "Rejected" -> StatusRejected.copy(alpha = 0.15f) to StatusRejected
        else -> Color.LightGray to Color.DarkGray
    }

    Text(
        text = status,
        color = textColor,
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}