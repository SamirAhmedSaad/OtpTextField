package com.samirahmed.otptextfield.ui

import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.google.common.truth.Truth.assertThat
import com.samirahmed.otptextfield.theme.OtpTextStyle
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OtpCellTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var otpCellProperties: OtpCellProperties

    @Before
    fun setUp() {
        otpCellProperties = OtpCellProperties(
            otpCellSize = 50.dp,
            otpTextStyle = OtpTextStyle,
            borderWidth = 1.dp,
            borderRound = 8.dp,
            hint = "*"
        )
    }

    @Test
    fun cellSize_verifySize_equal50dp() {
        var actualSize = 50
        composeTestRule.setContent {
            LocalDensity.current.apply {
                actualSize = actualSize.dp.roundToPx()
            }
            OtpCell(
                cellProperties = otpCellProperties,
                index = 2,
                text = "",
                isHasError = false,
                isHasCursor = false,
            )
        }

        val cellSize = composeTestRule
            .onNodeWithTag(TestingTags.CELL, useUnmergedTree = true)
            .fetchSemanticsNode().size
        assertThat(cellSize).isEqualTo(IntSize(actualSize, actualSize))
    }

    @Test
    fun cell_verify_indexDisplayCorrectCharacter() {
        val index = 2
        val text = "123"
        composeTestRule.setContent {
            OtpCell(
                cellProperties = otpCellProperties,
                index = index,
                text = text,
                isHasError = false,
                isHasCursor = false
            )
        }
        composeTestRule
            .onNodeWithTag(TestingTags.CELL_TEXT)
            .assert(hasText(text[index].toString()))
    }

    @Test
    fun cell_verify_ifTextIsEmpty_HintWillDisplay() {
        otpCellProperties = otpCellProperties.copy(
            otpLength = 6,
            hint = "*"
        )
        composeTestRule.setContent {
            OtpCell(
                cellProperties = otpCellProperties,
                index = otpCellProperties.otpLength,
                text = "",
                isHasError = false,
                isHasCursor = false
            )
        }
        composeTestRule.onNodeWithTag(TestingTags.CELL_TEXT)
            .assertTextContains(otpCellProperties.hint)
    }

}