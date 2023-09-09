package com.samirahmed.otptextfield.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.samirahmed.otptextfield.theme.Error_Color
import com.samirahmed.otptextfield.theme.Focus_Border
import com.samirahmed.otptextfield.theme.Focus_Text
import com.samirahmed.otptextfield.theme.unFocus_Border


@Composable
fun OtpCell(
    cellProperties: OtpCellProperties,
    index: Int,
    text: String,
    isHasError: Boolean
) {
    val isFocusedOtpCell = index <= text.length
    val cursorPosition = index == text.length
    val focusBorderColor =
        remember(isHasError) { if (isHasError) Error_Color else Focus_Border }
    val unFocusBorderColor =
        remember(isHasError) { if (isHasError) Error_Color else unFocus_Border }
    val textColor =
        remember(isHasError) { if (isHasError) Error_Color else Focus_Text }
    val char = when {
        index == text.length -> ""
        index > text.length -> cellProperties.hint
        else -> text[index].toString()
    }
    Box(
        modifier = Modifier
            .size(cellProperties.otpCellSize)
            .border(
                width = cellProperties.borderWidth,
                color = when {
                    isFocusedOtpCell -> focusBorderColor
                    else -> unFocusBorderColor
                },
                shape = RoundedCornerShape(cellProperties.borderRound)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            text = char,
            style = cellProperties.otpTextStyle,
            color = textColor,
            textAlign = TextAlign.Center
        )
        if (cursorPosition) {
            OtpCursor(
                cellProperties = cellProperties
            )
        }
    }
}
