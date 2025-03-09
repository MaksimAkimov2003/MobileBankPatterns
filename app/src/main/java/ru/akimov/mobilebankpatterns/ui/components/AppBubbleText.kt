package ru.akimov.mobilebankpatterns.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun AppBubbleText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 16.sp
        ),
        fontWeight = FontWeight(500),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}