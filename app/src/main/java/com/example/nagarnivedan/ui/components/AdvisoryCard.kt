package com.example.nagarnivedan.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nagarnivedan.ui.theme.*

@Composable
fun AdvisoryCard(text: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = DisabledBg)
    ) {
        Text(
            text = "• $text",
            modifier = Modifier.padding(14.dp),
            color = TextPrimary
        )
    }
}