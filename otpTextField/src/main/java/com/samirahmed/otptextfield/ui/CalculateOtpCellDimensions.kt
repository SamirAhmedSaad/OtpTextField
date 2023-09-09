package com.samirahmed.otptextfield.ui


fun prepareCellWidthIfNoSpace(
    screenWidth: Int,
    cellProperties: OtpCellProperties
): OtpCellProperties {
    var localCellProperties = cellProperties
    if (cellProperties.otpLength <= 0) {
        localCellProperties = cellProperties.copy(otpLength = 6)
    }
    val otpWidth =
        (localCellProperties.otpLength * localCellProperties.otpCellSize.value) + (localCellProperties.otpDistanceBetweenCells.value * (localCellProperties.otpLength - 1))

    return if (otpWidth <= screenWidth) {
        localCellProperties
    } else {
        calculateNewCellWidth(
            screenWidth = screenWidth,
            otpWidth = otpWidth,
            cellProperties = localCellProperties
        )
    }
}

fun calculateNewCellWidth(
    screenWidth: Int,
    otpWidth: Float,
    cellProperties: OtpCellProperties
): OtpCellProperties {

    val ratio = screenWidth / otpWidth
    val newCellSize = cellProperties.otpCellSize * ratio
    val newDistanceBetweenCells = cellProperties.otpDistanceBetweenCells * ratio
    val newTextSize = cellProperties.otpTextStyle.fontSize * ratio
    val newTextStyle = cellProperties.otpTextStyle.copy(
        fontSize = newTextSize
    )
    return cellProperties.copy(
        otpCellSize = newCellSize,
        otpDistanceBetweenCells = newDistanceBetweenCells,
        otpTextStyle = newTextStyle
    )
}