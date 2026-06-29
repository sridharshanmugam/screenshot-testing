package com.sridhar.screenshottesting.screenshot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.resources.NightMode
import com.sridhar.screenshottesting.image.ImageScreen
import com.sridhar.screenshottesting.pdf.PdfScreen
import com.sridhar.screenshottesting.profile.ProfileScreen
import com.sridhar.screenshottesting.ui.theme.ScreenshotTestingTheme
import com.sridhar.screenshottesting.util.DummyData
import com.sridhar.screenshottesting.watermark.WatermarkScreen
import org.junit.Rule
import org.junit.Test

/**
 * Author: sridhar.sa <sridhar.sa@zohocorp.com>
 * Date: 29/06/26, 4:08 pm
 */
class ScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    // ── Image ─────────────────────────────────────────────────────────────────

    @Test
    fun imageScreen_light() {
        DummyData.getImages().forEach { image ->
            paparazzi.snapshot(name = image.title) {
                ScreenshotTestingTheme(dynamicColor = false) {
                    ImageScreen(items = listOf(image))
                }
            }
        }
    }

    @Test
    fun imageScreen_dark() {
        paparazzi.unsafeUpdateConfig(
            deviceConfig = DeviceConfig.PIXEL_5.copy(nightMode = NightMode.NIGHT),
        )
        DummyData.getImages().forEach { image ->
            paparazzi.snapshot(name = image.title) {
                ScreenshotTestingTheme(darkTheme = true, dynamicColor = false) {
                    ImageScreen(items = listOf(image))
                }
            }
        }
    }

    // ── Pdf ───────────────────────────────────────────────────────────────────

    @Test
    fun pdfScreen_light() {
        DummyData.getPdfs().forEach { pdf ->
            paparazzi.snapshot(name = pdf.title) {
                ScreenshotTestingTheme(dynamicColor = false) {
                    PdfScreen(documents = listOf(pdf))
                }
            }
        }
    }

    @Test
    fun pdfScreen_dark() {
        paparazzi.unsafeUpdateConfig(
            deviceConfig = DeviceConfig.PIXEL_5.copy(nightMode = NightMode.NIGHT),
        )
        DummyData.getPdfs().forEach { pdf ->
            paparazzi.snapshot(name = pdf.title) {
                ScreenshotTestingTheme(darkTheme = true, dynamicColor = false) {
                    PdfScreen(documents = listOf(pdf))
                }
            }
        }
    }

    // ── Profile ───────────────────────────────────────────────────────────────

    @Test
    fun profileScreen_light() {
        DummyData.getProfiles().forEach { profile ->
            paparazzi.snapshot(name = profile.name) {
                ScreenshotTestingTheme(dynamicColor = false) {
                    ProfileScreen(profile = profile)
                }
            }
        }
    }

    @Test
    fun profileScreen_dark() {
        paparazzi.unsafeUpdateConfig(
            deviceConfig = DeviceConfig.PIXEL_5.copy(nightMode = NightMode.NIGHT),
        )
        DummyData.getProfiles().forEach { profile ->
            paparazzi.snapshot(name = profile.name) {
                ScreenshotTestingTheme(darkTheme = true, dynamicColor = false) {
                    ProfileScreen(profile = profile)
                }
            }
        }
    }

    // ── Watermark ─────────────────────────────────────────────────────────────

    @Test
    fun watermarkScreen_light() {
        DummyData.getWatermarks().forEachIndexed { index, watermark ->
            paparazzi.snapshot(name = "$index") {
                ScreenshotTestingTheme(dynamicColor = false) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        WatermarkScreen(
                            watermark = watermark,
                            skipTopAppBarPadding = true,
                        )
                    }
                }
            }
        }
    }

    @Test
    fun watermarkScreen_dark() {
        paparazzi.unsafeUpdateConfig(
            deviceConfig = DeviceConfig.PIXEL_5.copy(nightMode = NightMode.NIGHT),
        )
        DummyData.getWatermarks().forEachIndexed { index, watermark ->
            paparazzi.snapshot(name = "$index") {
                ScreenshotTestingTheme(darkTheme = true, dynamicColor = false) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        WatermarkScreen(
                            watermark = watermark,
                            skipTopAppBarPadding = true,
                        )
                    }
                }
            }
        }
    }
}
