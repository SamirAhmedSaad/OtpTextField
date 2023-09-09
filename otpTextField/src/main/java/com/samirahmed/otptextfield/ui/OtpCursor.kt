package com.samirahmed.otptextfield.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color


@Composable
fun OtpCursor(
    cellProperties: OtpCellProperties
) {
    val cursorWidth = cellProperties.cursorWidth
    val cursorAnimDuration = 1200
    val infiniteTransition = rememberInfiniteTransition(label = "cursor")
    val cursorColor by infiniteTransition.animateColor(
        initialValue = cellProperties.cursorColor,
        targetValue = Color.Transparent,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = cursorAnimDuration
                Color.Black at 0
                Color.Black at 800
                Color.Transparent at 900
            },
            repeatMode = RepeatMode.Restart
        ),
        label = "cursor_color"
    )
    Spacer(
        modifier = Modifier
            .width(cursorWidth)
            .fillMaxHeight(.7f)
            .drawBehind {
                drawRect(cursorColor)
            }
    )
}