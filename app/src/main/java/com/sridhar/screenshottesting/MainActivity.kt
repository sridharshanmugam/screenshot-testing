package com.sridhar.screenshottesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.School
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.sridhar.screenshottesting.image.ImageScreen
import com.sridhar.screenshottesting.pdf.PdfScreen
import com.sridhar.screenshottesting.profile.ProfileScreen
import com.sridhar.screenshottesting.ui.theme.ScreenshotTestingTheme
import com.sridhar.screenshottesting.util.DummyData
import com.sridhar.screenshottesting.watermark.WatermarkScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScreenshotTestingTheme {
                val title = ContextCompat.getString(
                    LocalContext.current,
                    R.string.title
                )
                ScreenshotTesting(title = title)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@PreviewScreenSizes
@Composable
fun ScreenshotTesting(title: String) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.Image) }
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations
                .entries
                .forEach {
                    item(
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.label
                            )
                        },
                        label = { Text(text = it.label) },
                        selected = it == currentDestination,
                        onClick = { currentDestination = it }
                    )
                }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = title) }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .fillMaxSize()
                    .padding(paddingValues = innerPadding)
            ) {
                when (currentDestination) {
                    AppDestinations.Image -> ImageScreen(items = DummyData.getImages())
                    AppDestinations.Pdf -> PdfScreen(documents = DummyData.getPdfs())
                    AppDestinations.Watermark -> WatermarkScreen(
                        watermark = DummyData.getWatermark(),
                        skipTopAppBarPadding = true,
                    )

                    AppDestinations.PROFILE -> ProfileScreen(profile = DummyData.getProfile())
                }
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    Image("Image", Icons.Rounded.Image),
    Pdf("Pdf", Icons.Rounded.Book),
    Watermark("Watermark", Icons.Rounded.School),
    PROFILE("Profile", Icons.Rounded.AccountBox),
}

@Preview()
@Composable
fun ScreenshotTestingPreview() {
    ScreenshotTestingTheme {
        val title = "Screenshot Testing"
        ScreenshotTesting(title = title)
    }
}