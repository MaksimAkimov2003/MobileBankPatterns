package ru.akimov.mobilebankpatterns.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import ru.akimov.mobilebankpatterns.ui.theme.accent

@Composable
fun AppButton(
    onClick: () -> Unit,
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    isAccent: Boolean = true,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        onClick = onClick,
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(38.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp
        ),
        colors = ButtonColors(
            containerColor = if (isAccent) MaterialTheme.colorScheme.accent else Color.Black,
            contentColor = if (isAccent) Color.Black else Color.White,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = icon, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text)
        }
    }
}