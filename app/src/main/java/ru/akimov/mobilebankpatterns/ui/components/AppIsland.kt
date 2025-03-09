package ru.akimov.mobilebankpatterns.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.akimov.mobilebankpatterns.AppConstants

@Composable
fun AppIsland(
    title: String,
    needSpacing: Boolean = true,
    bottomPadding: Dp = 16.dp,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = AppConstants.cardElevation,
        border = AppBorder(),
        shape = AppCardShape()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = bottomPadding)
        ) {
            Column {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500)
                )

                if (needSpacing) {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                content()
            }
        }
    }
}