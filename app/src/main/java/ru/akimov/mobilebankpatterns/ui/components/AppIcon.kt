package ru.akimov.mobilebankpatterns.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppIcon(
    color: Color,
    painter: Painter,
    cornerRadius: Dp = 10.dp,
    size: Dp = 32.dp,
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = color,
                shape = RoundedCornerShape(cornerRadius)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painter,
            contentDescription = null
        )
    }
}