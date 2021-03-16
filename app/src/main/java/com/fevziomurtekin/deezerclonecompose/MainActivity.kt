package com.fevziomurtekin.deezerclonecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fevziomurtekin.deezerclonecompose.ui.genre.GenreScreen
import com.fevziomurtekin.deezerclonecompose.ui.main.MainViewModel
import com.fevziomurtekin.deezerclonecompose.ui.main.SplashScreen
import com.fevziomurtekin.deezerclonecompose.ui.theme.DeezerCloneComposeTheme
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
    val isShownSplash = mainViewModel.splashShown.observeAsState(true)
    mainViewModel.showSplashScreen()

    if(isShownSplash.value) {
        SplashScreen()
    } else {
        GenreScreen(mainViewModel)
    }


}
