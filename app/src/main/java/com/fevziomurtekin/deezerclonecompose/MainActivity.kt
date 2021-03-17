package com.fevziomurtekin.deezerclonecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fevziomurtekin.deezerclonecompose.ui.genre.GenreScreen
import com.fevziomurtekin.deezerclonecompose.ui.main.MainViewModel
import com.fevziomurtekin.deezerclonecompose.ui.main.SplashScreen
import com.fevziomurtekin.deezerclonecompose.ui.theme.DeezerCloneComposeTheme
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
    val mainViewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    routesToScreen(navController)
    val isShownSplash = mainViewModel.splashShown.observeAsState(true)
    mainViewModel.showSplashScreen()

    if(isShownSplash.value) {
        SplashScreen()
    } else {
        GenreScreen(mainViewModel)
    }
}

private fun routesToScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.HOME) {
    }
}
