package com.samirahmed.otptextfield.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OtpTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var otpCellProperties: OtpCellProperties
    @Before
    fun setup() {
        otpCellProperties = OtpCellProperties(
            otpLength = 6,
            otpDistanceBetweenCells = 10.dp,
        )
    }
    @Test
    fun optTextField_Verify_otp_length_cells_isDisplayed() {
        otpCellProperties = otpCellProperties.copy(otpLength = 10)
        composeTestRule.setContent {
            OtpTextField(
                otpText = TextFieldValue(),
                otpCellProperties = otpCellProperties,
                onValueChange = {}
            )
        }

        composeTestRule
            .onAllNodesWithTag(TestingTags.CELL, true)
            .assertCountEquals(otpCellProperties.otpLength)
    }

    @Test
    fun optTextField_textFromOneToSex_NodeWithTextExist() {
        composeTestRule.setContent {
            OtpTextField(
                otpText = TextFieldValue("123456"),
                otpCellProperties = otpCellProperties,
                onValueChange = {}
            )
        }
        composeTestRule.onNodeWithText("123456").assertIsDisplayed()
    }

    @Test
    fun optTextField_typeText_NodeWithTextExist() {
        var otpText by mutableStateOf(TextFieldValue(""))
        composeTestRule.setContent {
            OtpTextField(
                otpText = otpText,
                otpCellProperties = otpCellProperties,
                onValueChange = {
                    if(it is OtpStatus.Typing) otpText = it.otp
                }
            )
        }
        composeTestRule.onNodeWithTag(TestingTags.OTP_TEXT_FIELD).performTextInput("123456")
        composeTestRule.onNodeWithText("123456").assertIsDisplayed()
    }

    @Test
    fun optTextField_otpFinished_OnFinishCallBackInvoked() {
        var otpStatus: OtpStatus? = null
        otpCellProperties = otpCellProperties.copy(otpLength = 6)
        var otpText by mutableStateOf(TextFieldValue(""))
        composeTestRule.setContent {
            OtpTextField(
                otpText = otpText,
                otpCellProperties = otpCellProperties,
                onValueChange = {
                    if(it is OtpStatus.Filled){
                        otpStatus = it
                    }
                }
            )
        }
        composeTestRule.onNodeWithTag(TestingTags.OTP_TEXT_FIELD).performTextInput("123456")
        Truth.assertThat(otpStatus).isInstanceOf(OtpStatus.Filled::class.java)
        Truth.assertThat(
            (otpStatus as OtpStatus.Filled).otp.text
        ).isEqualTo("123456")
    }

}