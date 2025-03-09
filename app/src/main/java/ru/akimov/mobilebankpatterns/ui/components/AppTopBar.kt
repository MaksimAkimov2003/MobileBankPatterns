package ru.akimov.mobilebankpatterns.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.akimov.mobilebankpatterns.ui.theme.subtitle

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    height: Dp = 64.dp,
    onBackClick: (() -> Unit)? = null,
    subtitle: String? = null,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        shadowElevation = 24.dp,
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 24.dp,
            bottomEnd = 24.dp,
        ),
    ) {
        if (subtitle == null) {
            OneLevel(onBackClick, title)
        } else {
            TwoLevel(onBackClick, title, subtitle)
        }
    }
}

@Composable
private fun TwoLevel(
    onBackClick: (() -> Unit)?,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 16.dp),
    ) {
        if (onBackClick != null) {
            BackButton(onClick = onBackClick, size = 42.dp)
        }
        Spacer(modifier = Modifier.width(36.dp))
        Column {
            Title(title = title)
            Subtitle(subtitle)
        }
    }
}

@Composable
private fun Subtitle(subtitle: String) {
    Text(
        text = subtitle,
        color = MaterialTheme.colorScheme.subtitle,
        fontSize = 18.sp,
    )
}

@Composable
private fun OneLevel(onBackClick: (() -> Unit)?, title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        onBackClick?.let {
            BackButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = it
            )
        }

        Title(
            title = title,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun Title(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = title,
        fontSize = 24.sp
    )
}

@Composable
private fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 32.dp
) {
    Row(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(16.dp))
                .clickable { onClick.invoke() },
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = null
        )
    }
}

