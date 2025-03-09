package ru.akimov.mobilebankpatterns.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun AppLayout(
    title: String,
    subtitle: String? = null,
    onBackClick: (() -> Unit)? = null,
    topBarHeight: Dp = 64.dp,
    footerHeight: Dp = 144.dp,
    showFooter: Boolean = false,
    showTopBar: Boolean = true,
    footerContent: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        if (showTopBar) {
            AppTopBar(
                subtitle = subtitle,
                onBackClick = onBackClick,
                modifier = Modifier.zIndex(1f),
                title = title,
                height = topBarHeight
            )
        }

        content()

        if (showFooter) {
            AppFooter(footerHeight) {
                footerContent()
            }
        }
    }
}