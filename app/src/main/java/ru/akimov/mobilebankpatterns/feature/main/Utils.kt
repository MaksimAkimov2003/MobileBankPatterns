package ru.akimov.mobilebankpatterns.feature.main

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.akimov.mobilebankpatterns.R
import ru.akimov.mobilebankpatterns.ui.theme.accent
import ru.akimov.mobilebankpatterns.ui.theme.grey
import ru.akimov.mobilebankpatterns.ui.theme.lightGreyColor
import ru.akimov.mobilebankpatterns.ui.theme.mint

@Composable
fun getBackgroundColor(type: Item.Type) = when (type) {
    Item.Type.ACCOUNT,
    Item.Type.CREDIT -> MaterialTheme.colorScheme.background

    Item.Type.BLOCKED_ACCOUNT -> MaterialTheme.colorScheme.lightGreyColor
}

@Composable
fun getTextColor(type: Item.Type) = when (type) {
    Item.Type.ACCOUNT,
    Item.Type.CREDIT -> MaterialTheme.colorScheme.onBackground

    Item.Type.BLOCKED_ACCOUNT -> MaterialTheme.colorScheme.grey
}

@Composable
fun getIcon(type: Item.Type) = when (type) {
    Item.Type.ACCOUNT -> painterResource(id = R.drawable.ic_card)
    Item.Type.CREDIT -> painterResource(id = R.drawable.ic_credit)
    Item.Type.BLOCKED_ACCOUNT -> painterResource(id = R.drawable.block)
}

@Composable
fun getIconColor(type: Item.Type) = when (type) {
    Item.Type.ACCOUNT -> MaterialTheme.colorScheme.accent
    Item.Type.CREDIT -> MaterialTheme.colorScheme.mint
    Item.Type.BLOCKED_ACCOUNT -> MaterialTheme.colorScheme.grey
}