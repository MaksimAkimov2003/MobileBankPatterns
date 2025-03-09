package ru.akimov.mobilebankpatterns.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun BoxScope.AppFooter(
    height: Dp,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .height(height)
            .zIndex(1f),
        shadowElevation = 12.dp,
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
        ),
        border = AppBorder()
    ) {
        content()
    }
}