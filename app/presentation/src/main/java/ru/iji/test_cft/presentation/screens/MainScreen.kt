package ru.iji.test_cft.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.iji.test_cft.presentation.R
import ru.iji.test_cft.presentation.components.RandomUserTopBar
import ru.iji.test_cft.presentation.screens.Route.DETAILS_SCREEN
import ru.iji.test_cft.presentation.screens.Route.FEED_SCREEN
import ru.iji.test_cft.presentation.theme.Test_CFTTheme
import ru.iji.test_cft.presentation.viewmodels.RandomUserViewModel

enum class Route(val title: Int) {
    FEED_SCREEN(title = R.string.feed_screen),
    DETAILS_SCREEN(title = R.string.details_screen),
}

@Composable
fun MainScreen(
    randomUserViewModel: RandomUserViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    closeSplashScreen: () -> Unit
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Route.valueOf(
        backStackEntry?.destination?.route ?: FEED_SCREEN.name
    )

    Test_CFTTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    RandomUserTopBar(
                        currentScreen = currentScreen,
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = { navController.navigateUp() }
                    )
                }
            ) {
                NavHost(
                    modifier = Modifier.padding(it),
                    navController = navController,
                    startDestination = FEED_SCREEN.name
                ) {
                    composable(FEED_SCREEN.name) {
                        RandomUserFeedScreen(
                            randomUserViewModel = randomUserViewModel,
                            navHostController = navController
                        )
                    }
                    composable(DETAILS_SCREEN.name) {
                        RandomUserDetailsScreen(
                            randomUserViewModel = randomUserViewModel
                        )
                    }
                }
            }
        }
    }

    closeSplashScreen()
}