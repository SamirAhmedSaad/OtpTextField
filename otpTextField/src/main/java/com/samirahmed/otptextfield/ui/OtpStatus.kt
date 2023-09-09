package com.samirahmed.otptextfield.ui


sealed class OtpStatus {
    class Filled(val otp: String) : OtpStatus()
}