package ru.akimov.mobilebankpatterns.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ru.akimov.mobilebankpatterns.ui.theme.stroke

@Composable
fun AppBorder() = BorderStroke(1.dp, MaterialTheme.colorScheme.stroke)