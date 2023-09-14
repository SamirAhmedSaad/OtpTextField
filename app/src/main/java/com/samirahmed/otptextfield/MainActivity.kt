package com.samirahmed.otptextfield

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samirahmed.otptextfield.ui.OtpCellProperties
import com.samirahmed.otptextfield.ui.OtpStatus
import com.samirahmed.otptextfield.ui.OtpTextField
import com.samirahmed.otptextfield.ui.theme.OtpTextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtpTextFieldTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    @Composable
    fun HomeScreen(modifier: Modifier = Modifier) {

        var otp by remember { mutableStateOf(TextFieldValue()) }
        var cellProperties by remember {
            mutableStateOf(
                OtpCellProperties(
                    otpLength = 6,
                    otpCellSize = 50.dp,
                    otpDistanceBetweenCells = 10.dp,
                    borderRound = 8.dp
                )
            )
        }

        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            OtpTextField(
                modifier = Modifier.fillMaxWidth(),
                otpText = otp,
                otpCellProperties = cellProperties,
                onValueChange = {otpStatus ->
                    when(otpStatus){
                        is OtpStatus.Typing -> {
                            otp = otpStatus.otp
                        }
                        is OtpStatus.Filled -> {
                            Toast.makeText(this@MainActivity, "OtpFinished", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(50.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShowHideError(
                    onCheckedChange = {
                        cellProperties = cellProperties.copy(
                            isHasError = it
                        )
                    }
                )

                IsHasCursor(
                    onCheckedChange = {
                        cellProperties = cellProperties.copy(
                            isHasCursor = it
                        )
                    }
                )
            }

            AddTextMask(
                onCheckedChange = {
                    cellProperties = cellProperties.copy(
                        mask = if(it) "*" else ""
                    )
                }
            )

            Spacer(modifier = Modifier.height(10.dp))
            SliderWithTitle(
                title = "Otp length",
                initValue = cellProperties.otpLength.toFloat(),
                valueRange = 2f..15f,
                onValueChanged = {
                    cellProperties = cellProperties.copy(
                        otpLength = it.toInt()
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SliderWithTitle(
                title = "Otp cell size",
                initValue = cellProperties.otpCellSize.value,
                valueRange = 30f..70f,
                onValueChanged = {
                    cellProperties = cellProperties.copy(
                        otpCellSize = it.dp
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SliderWithTitle(
                title = "Distance between cells",
                initValue = cellProperties.otpDistanceBetweenCells.value,
                valueRange = 2f..15f,
                onValueChanged = {
                    cellProperties = cellProperties.copy(
                        otpDistanceBetweenCells = it.dp
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SliderWithTitle(
                title = "Border Round",
                initValue = cellProperties.borderRound.value,
                valueRange = 5f..50f,
                onValueChanged = {
                    cellProperties = cellProperties.copy(
                        borderRound = it.dp
                    )
                }
            )
        }
    }

    @Composable
    fun ShowHideError(
        onCheckedChange: (Boolean) -> Unit,
    ) {
        var showError by remember {
            mutableStateOf(false)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = showError,
                onCheckedChange = {
                    showError = it
                    onCheckedChange(it)
                }
            )
            Text(text = "Show Error")
        }
    }

    @Composable
    fun IsHasCursor(
        onCheckedChange: (Boolean) -> Unit,
    ) {
        var isHasCursor by remember {
            mutableStateOf(true)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isHasCursor,
                onCheckedChange = {
                    isHasCursor = it
                    onCheckedChange(it)
                }
            )
            Text(text = "Is Has Cursor")
        }
    }

    @Composable
    fun AddTextMask(
        onCheckedChange: (Boolean) -> Unit,
    ) {
        var isTextMask by remember {
            mutableStateOf(false)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isTextMask,
                onCheckedChange = {
                    isTextMask = it
                    onCheckedChange(it)
                }
            )
            Text(text = "Add * text mask")
        }
    }

    @Composable
    fun SliderWithTitle(
        title: String,
        initValue: Float,
        valueRange: ClosedFloatingPointRange<Float>,
        onValueChanged: (Float) -> Unit
    ) {
        var sliderPosition by remember { mutableStateOf(initValue) }

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(title)
            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                    onValueChanged(it)
                },
                valueRange = valueRange,

                )
        }
    }

    @Preview(
        device = "id:pixel_xl",
        showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true
    )
    @Composable
    fun HomeScreenPreview() {
        HomeScreen()
    }

}