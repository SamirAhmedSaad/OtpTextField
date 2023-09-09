package com.samirahmed.otptextfield.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly


@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: TextFieldValue = TextFieldValue(),
    isHasError: Boolean = false,
    otpCellProperties: OtpCellProperties = OtpCellProperties(),
    onValueChange: (TextFieldValue) -> Unit = {},
    onOtpFinished: (OtpStatus) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val cellProperties = remember(otpCellProperties) {
        prepareCellWidthIfNoSpace(
            screenWidth = screenWidth,
            cellProperties = otpCellProperties
        )
    }

    CompositionLocalProvider(LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Ltr) {
        BasicTextField(
            value = otpText,
            onValueChange = {
                if (it.text.isDigitsOnly() && it.text.length <= cellProperties.otpLength) {
                    onValueChange(it)
                    if (it.text.length == cellProperties.otpLength) {
                        onOtpFinished(OtpStatus.Filled(it.text))
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            cursorBrush = SolidColor(Color.Transparent),
            decorationBox = {
                OtpDecorationBox(
                    modifier = modifier,
                    cellProperties = cellProperties,
                    otpText = otpText,
                    isHasError = isHasError,
                )
            }
        )
    }

}

@Composable
fun OtpDecorationBox(
    modifier: Modifier = Modifier,
    cellProperties: OtpCellProperties,
    otpText: TextFieldValue,
    isHasError: Boolean,
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(cellProperties.otpDistanceBetweenCells)
        ) {
            repeat(cellProperties.otpLength) { index ->
                OtpCell(
                    cellProperties = cellProperties,
                    index = index,
                    text = otpText.text,
                    isHasError = isHasError
                )
            }
        }
    }
}

@Preview(
    device = "id:pixel_xl",
    showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true
)
@Composable
fun OtpTextFieldPreview() {
    Column {
        OtpTextField(
            onOtpFinished = {}
        )
    }
}