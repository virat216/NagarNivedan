package com.example.nagarnivedan.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nagarnivedan.ui.theme.*

@Composable
fun ReviewCard(
    title: String,
    value: String,
    actionText: String,
    onclick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = DisabledBg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = title,
                    color = BluePrimary,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = value,
                    color = TextPrimary
                )
            }

            OutlinedButton(onClick = onclick,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = BluePrimary
                ),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    BluePrimary.copy(alpha = 0.6f)
                )
            ){
                Text(text = actionText,
                    style = MaterialTheme.typography.bodyMedium)

            }
        }
    }
}