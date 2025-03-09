package ru.akimov.mobilebankpatterns.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.akimov.mobilebankpatterns.ui.theme.overlayTransparent

@Composable
fun AppLoader(
    isTransparent: Boolean = true
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .run {
                if (isTransparent) {
                    background(MaterialTheme.colorScheme.overlayTransparent)
                } else this
            },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}