package ru.akimov.mobilebankpatterns.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val ColorScheme.overlayTransparent: Color
    get() = OverlayTransparentColor
val ColorScheme.stroke: Color
    get() = StrokeColor
val ColorScheme.accent: Color
    get() = AccentColor
val ColorScheme.mint: Color
    get() = MintColor
val ColorScheme.grey: Color
    get() = GreyColor
val ColorScheme.lightGreyColor: Color
    get() = LightGreyColor
val ColorScheme.greenColor: Color
    get() = GreenColor
val ColorScheme.purpleColor: Color
    get() = PurpleColor
val ColorScheme.subtitle: Color
    get() = Color(0xFF756D6D)

private val OverlayTransparentColor = Color(0xFF03080D).copy(alpha = 0.24f)
private val StrokeColor = Color(0xFFCDC4C4)
private val AccentColor = Color(0xFFF2FE8D)
private val MintColor = Color(0xFFB2D0CE)
private val GreyColor = Color(0xFF79767D)
private val LightGreyColor = Color(0xFFDDDDDD)
private val GreenColor = Color(0xFF0D8352)
private val PurpleColor = Color(0xFFAA9EB7)