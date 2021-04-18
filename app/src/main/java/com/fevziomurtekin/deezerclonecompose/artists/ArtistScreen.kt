package com.fevziomurtekin.deezerclonecompose.artists

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistDetailResponse
import com.fevziomurtekin.deezerclonecompose.main.DeezerViewModel
import com.fevziomurtekin.deezerclonecompose.ui.util.CircularLoadingView
import com.fevziomurtekin.deezerclonecompose.ui.util.ErrorScreen
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState
import dev.chrisbanes.accompanist.imageloading.MaterialLoadingImage
import kotlinx.coroutines.launch

@Composable
fun ArtistScreen(
    navController: NavHostController,
    viewModel: DeezerViewModel,
    artistId: Int
) {
    val scope = rememberCoroutineScope()
    viewModel.fetchArtistDetails(artistId.toString())
    val artistDetails = viewModel.artistDetailState
    when (val result = artistDetails.value) {
        is DeezerResult.Success -> {
            ArtistDetail(result.data,
                    viewModel,
                    artistId,
                    navController)
        }
        is DeezerResult.Error -> {
            ErrorScreen(retryClick = {
                scope.launch {
                    /* TODO retry click */
                }
            })
        }
        else -> {
            CircularLoadingView()
        }
    }
}

@Composable
fun ArtistDetail(
    details: ArtistDetailResponse,
    viewModel: DeezerViewModel,
    artistId: Int,
    navController: NavHostController
) {
    Column {
        Box {
            CoilImage(data = details.picture_big) { imageState ->
                when (imageState) {
                    is ImageLoadState.Success -> {
                        Box(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                        ) {
                            MaterialLoadingImage(
                                    result = imageState,
                                    contentDescription = null,
                                    fadeInEnabled = true,
                                    fadeInDurationMs = 600,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.FillBounds,
                                    skipFadeWhenLoadedFromMemory = true
                            )
                        }
                    }
                    else -> Unit
                }
            }
            Text(
                    modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .padding(8.dp),
                    text = details.name, fontSize = 20.sp, color = Color.White)
        }
        ArtistAlbumScreen(artistId, viewModel, navController)
    }
}