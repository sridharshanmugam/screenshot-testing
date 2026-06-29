package com.sridhar.screenshottesting.watermark

/**
 * Author: sridhar.sa <sridhar.sa@zohocorp.com>
 * Date: 29/06/26, 2:01 pm
 */
data class Watermark(
    val displayLearnerEmail: Boolean,
    val displayAcademyLogo: Boolean,
    val displayAcademyName: Boolean,
    val imageSize: Int,
    val textSize: Int,
    val horizontalSpacing: Int,
    val verticalSpacing: Int,
    val opacity: Float,
    val position: WatermarkPosition,
    val redColorComponent: Int,
    val greenColorComponent: Int,
    val blueColorComponent: Int,
    val academyLogoUrl: String?,
    val academyName: String?,
    val learnerEmail: String?,
    val style: WatermarkStyle,
    val rotationDegree: Float,
)

enum class WatermarkPosition {
    TOP_LEFT,
    TOP_CENTER,
    TOP_RIGHT,
    CENTER_LEFT,
    CENTER,
    CENTER_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_CENTER,
    BOTTOM_RIGHT,
    ;

    companion object {
        fun fromString(value: String): WatermarkPosition? = entries
            .firstOrNull { it.name == value }
    }
}

enum class WatermarkStyle {
    FIXED,
    DIAGONAL,
    ;

    companion object {
        fun fromString(value: String): WatermarkStyle? = entries
            .firstOrNull { it.name == value }
    }
}
