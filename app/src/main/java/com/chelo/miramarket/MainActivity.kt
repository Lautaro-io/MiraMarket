package com.chelo.miramarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.chelo.miramarket.presentation.navigation.NavigationWrapper
import com.chelo.miramarket.ui.theme.MiraMarketTheme
import dagger.hilt.android.AndroidEntryPoint
import org.maplibre.android.MapLibre
import org.maplibre.android.WellKnownTileServer
import org.osmdroid.config.Configuration

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sp = installSplashScreen()
        Configuration.getInstance().userAgentValue = packageName
        MapLibre.getInstance(
            this,
            "30CNXncovO6BSPiwby70",
            WellKnownTileServer.MapLibre
        )
        setContent {
            MiraMarketTheme(darkTheme = false) {
                NavigationWrapper()
            }
        }
    }
}

