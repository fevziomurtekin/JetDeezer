package com.fevziomurtekin.deezerclonecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.fevziomurtekin.deezerclonecompose.ui.details.DetailScreen
import com.fevziomurtekin.deezerclonecompose.ui.home.HomeScreen
import com.fevziomurtekin.deezerclonecompose.ui.main.DeezerViewModel
import com.fevziomurtekin.deezerclonecompose.ui.main.SplashScreen
import com.fevziomurtekin.deezerclonecompose.ui.theme.DeezerCloneComposeTheme
import com.fevziomurtekin.deezerclonecompose.ui.util.ScreenArguments
import com.fevziomurtekin.deezerclonecompose.ui.util.Screens
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeezerCloneComposeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val deezerViewModel: DeezerViewModel = viewModel()
    val navController = rememberNavController()
    RoutesToScreen(navController, deezerViewModel)
    val isShownSplash = deezerViewModel.splashShown.observeAsState(true)
    deezerViewModel.showSplashScreen()

    if(isShownSplash.value) {
        SplashScreen()
    } else {
        HomeScreen(navController, deezerViewModel)
    }
}

@Composable
private fun RoutesToScreen(
        navController: NavHostController,
        viewModel: DeezerViewModel
) {
    NavHost(navController = navController, startDestination = Screens.HOME, builder = {
        /*
        * composable(Routes.ONBOARDING_SCREEN) {
            OnboardingScreen(navController = navController)
        }
        */
        composable(Screens.HOME) {
            HomeScreen(navController, viewModel)
        }

        composable(
                route = Screens.DETAILS,
                arguments = listOf(navArgument(ScreenArguments.GENRE_ID) { type = NavType.IntType})
        ) {
            it.arguments?.getInt(ScreenArguments.GENRE_ID)?.let { id ->
                DetailScreen(navController, viewModel, id)
            }
        }
    })
}
