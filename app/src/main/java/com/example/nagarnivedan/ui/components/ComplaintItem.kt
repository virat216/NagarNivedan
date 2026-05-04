package com.example.nagarnivedan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nagarnivedan.data.Complaint
import com.example.nagarnivedan.ui.theme.*
import com.example.nagarnivedan.ui.theme.TextPrimary
import com.example.nagarnivedan.ui.theme.TextSecondary
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.nagarnivedan.ui.theme.StatusResolved
import com.example.nagarnivedan.ui.theme.StatusRegistered
import com.example.nagarnivedan.ui.theme.StatusInProgress
import com.example.nagarnivedan.ui.theme.StatusRejected

@Composable
fun ComplaintItem(
    complaint: Complaint,
    onClick: () -> Unit
) {

    val statusColor = when (complaint.status) {
        "Resolved" -> StatusResolved
        "Registered" -> StatusRegistered
        "In Progress" -> StatusInProgress
        "Rejected" -> StatusRejected
        else -> TextSecondary
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        BluePrimary.copy(alpha = 0.1f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Report,
                    contentDescription = null,
                    tint = BluePrimary
                )
            }

            Spacer(modifier = Modifier.width(12.dp))


            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = complaint.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = complaint.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    maxLines = 1
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(statusColor, shape = CircleShape)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = complaint.status,
                    color = statusColor,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}