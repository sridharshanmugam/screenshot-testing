package com.sridhar.screenshottesting.util

import com.sridhar.screenshottesting.image.Image
import com.sridhar.screenshottesting.pdf.Pdf
import com.sridhar.screenshottesting.profile.Profile
import com.sridhar.screenshottesting.watermark.Watermark
import com.sridhar.screenshottesting.watermark.WatermarkPosition
import com.sridhar.screenshottesting.watermark.WatermarkStyle

/**
 * Author: sridhar.sa <sridhar.sa@zohocorp.com>
 * Date: 29/06/26, 2:03 pm
 */
object DummyData {
    fun getWatermark(): Watermark = Watermark(
        displayLearnerEmail = true,
        displayAcademyLogo = true,
        displayAcademyName = true,
        imageSize = 70,
        textSize = 16,
        horizontalSpacing = 8,
        verticalSpacing = 4,
        opacity = 1f,
        position = WatermarkPosition.TOP_LEFT,
        redColorComponent = 255,
        greenColorComponent = 255,
        blueColorComponent = 255,
        academyLogoUrl = "https://example.com/logo.png",
        academyName = "Education",
        learnerEmail = "user@mail.com",
        style = WatermarkStyle.FIXED,
        rotationDegree = 0f,
    )

    fun getWatermarks(): List<Watermark> {
        val setting = getWatermark()
        val blackTextColorSetting = setting.copy(
            redColorComponent = 0,
            greenColorComponent = 0,
            blueColorComponent = 0,
        )

        // Different size variants applied with the [WatermarkSettingMapper.Companion.SCALE_FACTOR].
        val size75Setting = setting.copy(
            imageSize = 59,
            textSize = 14,
            horizontalSpacing = 4,
            verticalSpacing = 2,
            opacity = 0.75f,
        )
        val size50Setting = setting.copy(
            imageSize = 49,
            textSize = 11,
            horizontalSpacing = 2,
            verticalSpacing = 1,
            opacity = 0.5f,
            position = WatermarkPosition.CENTER,
        )
        val size25Setting = setting.copy(
            imageSize = 38,
            textSize = 9,
            horizontalSpacing = 1,
            verticalSpacing = 0,
            opacity = 0.25f,
            position = WatermarkPosition.BOTTOM_RIGHT,
        )

        val diagonalSetting = setting.copy(
            style = WatermarkStyle.DIAGONAL,
            rotationDegree = -30f,
        )
        val repeatingSetting = setting.copy(
            style = WatermarkStyle.DIAGONAL,
            rotationDegree = 30f,
        )

        // All combinations of displayAcademyLogo, displayAcademyName, and displayLearnerEmail.
        val logoOnlySetting = setting.copy(
            displayLearnerEmail = false,
            displayAcademyLogo = true,
            displayAcademyName = false,
        )
        val nameOnlySetting = setting.copy(
            displayLearnerEmail = false,
            displayAcademyLogo = false,
            displayAcademyName = true,
        )
        val emailOnlySetting = setting.copy(
            displayLearnerEmail = true,
            displayAcademyLogo = false,
            displayAcademyName = false,
        )
        val logoAndNameSetting = setting.copy(
            displayLearnerEmail = false,
            displayAcademyLogo = true,
            displayAcademyName = true,
        )
        val logoAndEmailSetting = setting.copy(
            displayLearnerEmail = true,
            displayAcademyLogo = true,
            displayAcademyName = false,
        )
        val nameAndEmailSetting = setting.copy(
            displayLearnerEmail = true,
            displayAcademyLogo = false,
            displayAcademyName = true,
        )
        return buildList {
            add(setting)
            add(blackTextColorSetting)
            add(size75Setting)
            add(size50Setting)
            add(size25Setting)
            add(diagonalSetting)
            add(repeatingSetting)
            add(logoOnlySetting)
            add(nameOnlySetting)
            add(emailOnlySetting)
            add(logoAndNameSetting)
            add(logoAndEmailSetting)
            add(nameAndEmailSetting)
        }
    }

    fun getImages(): List<Image> = listOf(
        Image(
            id = 1,
            title = "Mountain Sunrise",
            url = "https://example.com/img/mountain-sunrise.jpg",
            widthPx = 4032,
            heightPx = 3024,
            tags = listOf("nature", "landscape", "morning"),
        ),
        Image(
            id = 2,
            title = "City Skyline at Dusk",
            url = "https://example.com/img/city-skyline.jpg",
            widthPx = 5472,
            heightPx = 3648,
            tags = listOf("urban", "architecture", "night"),
        ),
        Image(
            id = 3,
            title = "Forest Trail",
            url = "https://example.com/img/forest-trail.jpg",
            widthPx = 3024,
            heightPx = 4032,
            tags = listOf("nature", "forest", "hiking"),
        ),
        Image(
            id = 4,
            title = "Ocean Waves",
            url = "https://example.com/img/ocean-waves.jpg",
            widthPx = 6000,
            heightPx = 4000,
            tags = listOf("sea", "waves", "travel"),
        ),
        Image(
            id = 5,
            title = "Desert Dunes",
            url = null,
            widthPx = 4800,
            heightPx = 3200,
            tags = listOf("desert", "sand", "landscape"),
        ),
    )

    fun getPdfs(): List<Pdf> = listOf(
        Pdf(
            id = 1,
            title = "Android Development Guide",
            pageCount = 142,
            fileSizeKb = 3_840,
            lastOpenedAt = "Today",
        ),
        Pdf(
            id = 2,
            title = "Jetpack Compose in Action",
            pageCount = 89,
            fileSizeKb = 2_210,
            lastOpenedAt = "Yesterday",
        ),
        Pdf(
            id = 3,
            title = "Kotlin Coroutines Deep Dive",
            pageCount = 217,
            fileSizeKb = 5_630,
            lastOpenedAt = "3 days ago",
        ),
        Pdf(
            id = 4,
            title = "Material Design 3 Spec",
            pageCount = 64,
            fileSizeKb = 1_450,
            lastOpenedAt = "Last week",
        ),
        Pdf(
            id = 5,
            title = "Clean Architecture Patterns",
            pageCount = 178,
            fileSizeKb = 4_100,
            lastOpenedAt = "2 weeks ago",
        ),
    )

    fun getProfile(): Profile = Profile(
        name = "Alex Johnson",
        email = "alex.johnson@example.com",
        avatarUrl = null,
        bio = "Lifelong learner passionate about technology, design, and building great mobile experiences.",
        memberSince = "January 2023",
        completedCourses = 12,
        inProgressCourses = 3,
    )

    fun getProfiles(): List<Profile> = listOf(
        getProfile(),
        Profile(
            name = "Maria Garcia",
            email = "maria.garcia@example.com",
            avatarUrl = null,
            bio = "",
            memberSince = "March 2024",
            completedCourses = 3,
            inProgressCourses = 1,
        ),
        Profile(
            name = "David Chen",
            email = "david.chen@example.com",
            avatarUrl = null,
            bio = "Software engineer specializing in Android development and system design.",
            memberSince = "June 2022",
            completedCourses = 47,
            inProgressCourses = 5,
        ),
    )
}
