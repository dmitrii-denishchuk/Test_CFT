package ru.iji.test_cft.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ru.iji.test_cft.presentation.screens.MainScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepOnScreenCondition = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            keepOnScreenCondition
        }

        setContent {
            MainScreen { keepOnScreenCondition = false }
        }
    }
}