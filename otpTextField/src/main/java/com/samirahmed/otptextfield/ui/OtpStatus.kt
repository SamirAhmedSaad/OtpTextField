package com.samirahmed.otptextfield.ui

import androidx.compose.ui.text.input.TextFieldValue


sealed class OtpStatus {
    data class Filled(val otp: TextFieldValue) : OtpStatus()
    data class Typing(val otp: TextFieldValue) : OtpStatus()
}