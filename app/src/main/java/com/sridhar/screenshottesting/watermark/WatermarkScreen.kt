package com.sridhar.screenshottesting.watermark

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.School
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sridhar.screenshottesting.ui.theme.ScreenshotTestingTheme
import com.sridhar.screenshottesting.util.DummyData

/**
 * Author: sridhar.sa <sridhar.sa@zohocorp.com>
 * Date: 29/06/26, 2:15 pm
 */


@Composable
fun BoxScope.WatermarkScreen(
    watermark: Watermark,
    skipTopAppBarPadding: Boolean,
) {
    when (watermark.style) {
        WatermarkStyle.FIXED -> {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(
                        top = if (LocalInspectionMode.current || skipTopAppBarPadding) {
                            0.dp
                        } else {
                            MaterialTopAppBarHeight
                        },
                    )
                    .alpha(alpha = watermark.opacity),
                contentAlignment = getWatermarkPositionAlignment(position = watermark.position),
            ) {
                WatermarkContent(watermark = watermark)
            }
        }

        WatermarkStyle.DIAGONAL -> {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(alpha = watermark.opacity),
                contentAlignment = Alignment.Center,
            ) {
                WatermarkContent(
                    watermark = watermark,
                    degree = watermark.rotationDegree,
                )
            }
        }
    }
}

@Composable
private fun WatermarkContent(
    watermark: Watermark,
    padding: Dp = 8.dp,
    degree: Float = 0f,
    academyLogoPainter: Painter? = null,
) {
    val textColor = remember(key1 = watermark) {
        Color(
            red = watermark.redColorComponent,
            green = watermark.greenColorComponent,
            blue = watermark.blueColorComponent,
        )
    }
    Row(
        modifier = Modifier
            .padding(all = padding)
            .rotate(degrees = degree),
        horizontalArrangement = Arrangement.spacedBy(space = watermark.horizontalSpacing.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AcademyLogo(
            watermark = watermark,
            painter = academyLogoPainter,
            color = textColor,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(space = watermark.verticalSpacing.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            if (watermark.displayAcademyName && watermark.academyName != null) {
                Text(
                    text = watermark.academyName,
                    color = textColor,
                    fontSize = watermark.textSize.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    style = TextStyle(
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        ),
                    ),
                )
            }
            if (watermark.displayLearnerEmail && watermark.learnerEmail != null) {
                Text(
                    text = watermark.learnerEmail,
                    color = textColor,
                    fontSize = watermark.textSize.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    style = TextStyle(
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        ),
                    ),
                )
            }
        }
    }
}

@Composable
private fun AcademyLogo(
    watermark: Watermark,
    painter: Painter?,
    color: Color,
) {
    val circleModifier = Modifier
        .padding(all = 4.dp)
        .size(size = watermark.imageSize.dp)
        .clip(shape = CircleShape)
        .background(color = Color.Black, shape = CircleShape)
        .padding(all = 8.dp)

    if (painter != null) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = circleModifier,
        )
    } else {
        if (LocalInspectionMode.current) {
            Image(
                imageVector = Icons.Rounded.School,
                contentDescription = null,
                modifier = circleModifier,
                colorFilter = ColorFilter.tint(color = color),
            )
        } else {
            if (watermark.displayAcademyLogo && watermark.academyLogoUrl.isNullOrBlank().not()) {
                Image(
                    imageVector = Icons.Rounded.School,
                    contentDescription = null,
                    modifier = circleModifier,
                    colorFilter = ColorFilter.tint(color = color),
                )
            }
        }
    }
}

private fun getWatermarkPositionAlignment(position: WatermarkPosition): Alignment {
    return when (position) {
        WatermarkPosition.TOP_LEFT -> Alignment.TopStart
        WatermarkPosition.TOP_CENTER -> Alignment.TopCenter
        WatermarkPosition.TOP_RIGHT -> Alignment.TopEnd
        WatermarkPosition.CENTER_LEFT -> Alignment.CenterStart
        WatermarkPosition.CENTER -> Alignment.Center
        WatermarkPosition.CENTER_RIGHT -> Alignment.CenterEnd
        WatermarkPosition.BOTTOM_LEFT -> Alignment.BottomStart
        WatermarkPosition.BOTTOM_CENTER -> Alignment.BottomCenter
        WatermarkPosition.BOTTOM_RIGHT -> Alignment.BottomEnd
    }
}

private val MaterialTopAppBarHeight: Dp = 64.dp

//<=========================================== Previews ===========================================>
class WatermarkPreviewParameter : PreviewParameterProvider<Watermark> {
    override val values = DummyData.getWatermarks().asSequence()
}

@Preview
@Composable
fun PdfWatermarkPreview(
    @PreviewParameter(WatermarkPreviewParameter::class)
    watermark: Watermark,
) {
    ScreenshotTestingTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(Color.DarkGray),
            ) {
                WatermarkScreen(
                    watermark = watermark,
                    skipTopAppBarPadding = true
                )
            }
        }
    }
}
