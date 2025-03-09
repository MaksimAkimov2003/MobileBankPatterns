package ru.akimov.mobilebankpatterns.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppBubble(
    modifier: Modifier = Modifier,
    heightInMin: Dp = 108.dp,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier.heightIn(min = heightInMin),
        border = AppBorder(),
        shape = AppCardShape(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.padding(12.dp),
        ) {
            content()
        }
    }
}