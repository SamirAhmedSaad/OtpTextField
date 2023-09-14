package com.samirahmed.otptextfield.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.samirahmed.otptextfield.theme.OtpTextStyle

data class OtpCellProperties(
    val otpLength: Int = 6,
    val otpCellSize: Dp = 50.dp,
    val otpDistanceBetweenCells: Dp = 10.dp,
    val otpTextStyle: TextStyle = OtpTextStyle,
    val borderWidth: Dp = 1.dp,
    val borderRound: Dp = 8.dp,
    val cursorWidth: Dp = 2.dp,
    val cursorColor: Color = Color.Black,
    val isHasError: Boolean = false,
    val isHasCursor: Boolean = true,
    val hint: String = "",
    val mask: String = ""
)
