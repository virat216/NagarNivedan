package com.example.nagarnivedan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nagarnivedan.data.Alert
import com.example.nagarnivedan.ui.theme.*

@Composable
fun AlertItem(
    alert: Alert,
    onClick: () -> Unit
) {
    val isRead = alert.isRead

    val backgroundColor = if (isRead) DisabledBg else White
    val titleColor = if (isRead) TextSecondary else TextPrimary

    val icon = when (alert.type) {
        "Water" -> Icons.Default.WaterDrop
        "Road" -> Icons.Default.Construction
        "Electricity" -> Icons.Default.Bolt
        else -> Icons.Default.Info
    }

    val chipColor = when (alert.type) {
        "Water" -> BluePrimary.copy(alpha = 0.1f)
        "Road" -> StatusInProgress.copy(alpha = 0.15f)
        "Electricity" -> StatusPending.copy(alpha = 0.15f)
        else -> DisabledBg
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if(!isRead) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(BluePrimary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = BluePrimary)
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = alert.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = titleColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = alert.location,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }

            Column(horizontalAlignment = Alignment.End) {

                Text(
                    text = alert.time,
                    color = BluePrimary,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(6.dp))

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = TextSecondary
                )
            }
        }
    }
}