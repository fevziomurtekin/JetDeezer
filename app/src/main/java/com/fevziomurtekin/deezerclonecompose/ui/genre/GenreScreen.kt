package com.fevziomurtekin.deezerclonecompose.ui.genre

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.response.Genre
import com.fevziomurtekin.deezerclonecompose.ui.main.MainViewModel
import com.fevziomurtekin.deezerclonecompose.ui.util.CircularLoadingView
import com.fevziomurtekin.deezerclonecompose.ui.util.ErrorScreen
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState
import dev.chrisbanes.accompanist.imageloading.MaterialLoadingImage
import kotlinx.coroutines.launch

@Composable
fun GenreScreen(viewModel: MainViewModel) {
    val scope = rememberCoroutineScope()
    viewModel.fetchGenreList()
    val genres = viewModel.genreState
    when(val result = genres.value) {
        is DeezerResult.Error -> {
            ErrorScreen(retryClick = {
                scope.launch {
                    /* TODO retry click */
                }
            })
        }
        is DeezerResult.Success -> {
           result.data?.let { GenreList(it) }
        }
        else -> {
            CircularLoadingView()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenreList(list: List<Genre>) {
    LazyVerticalGrid(cells = GridCells.Fixed(2) ) {
        items(list) { genre ->
            CoilImage(data = genre.picture) { imageState ->
                when (imageState) {
                    is ImageLoadState.Success -> {
                        Card(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.5f)
                                .padding(8.dp),
                                elevation = 16.dp,
                                shape = RoundedCornerShape(16.dp)
                        ) {
                            Box(
                               modifier = Modifier.fillMaxSize(),
                               contentAlignment = Alignment.Center
                            ) {
                                MaterialLoadingImage(
                                        result = imageState,
                                        contentDescription = null,
                                        fadeInEnabled = true,
                                        fadeInDurationMs = 600,
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop,
                                        skipFadeWhenLoadedFromMemory = true
                                )
                                Text(text = genre.name, fontSize = 32.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold

                                )
                            }
                        }

                    }
                    else -> Unit
                }

            }
        }
    }
}