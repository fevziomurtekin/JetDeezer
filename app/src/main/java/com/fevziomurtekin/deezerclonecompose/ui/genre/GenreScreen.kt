package com.fevziomurtekin.deezerclonecompose.ui.genre

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.ui.main.MainViewModel
import com.fevziomurtekin.deezerclonecompose.ui.util.CircularLoadingView

@Composable
fun GenreScreen(viewModel: MainViewModel) {
    viewModel.fetchGenreList()
    val response =
        viewModel.genreListLiveData.collectAsState(DeezerResult.Loading)
    when(response.value) {
        is DeezerResult.Error -> {

        }
        is DeezerResult.Success -> {

        }
        else -> {
            CircularLoadingView()
        }
    }
}