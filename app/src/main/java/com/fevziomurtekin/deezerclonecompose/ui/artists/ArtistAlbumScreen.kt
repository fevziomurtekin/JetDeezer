package com.fevziomurtekin.deezerclonecompose.ui.artists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistAlbumData
import com.fevziomurtekin.deezerclonecompose.ui.main.DeezerViewModel
import com.fevziomurtekin.deezerclonecompose.ui.util.CircularLoadingView
import com.fevziomurtekin.deezerclonecompose.ui.util.ErrorScreen
import com.fevziomurtekin.deezerclonecompose.ui.util.Screens
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState
import dev.chrisbanes.accompanist.imageloading.MaterialLoadingImage
import kotlinx.coroutines.launch

@Composable
fun ArtistAlbumScreen(
        artistID: Int,
        viewModel: DeezerViewModel,
        navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    viewModel.fetchArtistAlbums(artistID.toString())
    val artistAlbumState = viewModel.artistAlbumsState
    when(val result = artistAlbumState.value) {
        is DeezerResult.Success -> {
            ArtistAlbums(
                    result.data.data,
                    navController
            )
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
fun ArtistAlbums(
        list: List<ArtistAlbumData>,
        navController: NavHostController
) {
    LazyColumn{
        items(list.size){ index ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
                .clickable {
                    navController.navigate(Screens.createAlbumsDetails(list[index].id))
                })
            {
                Row(modifier = Modifier
                    .fillMaxSize()){
                    CoilImage(data = list[index].cover_medium) { imageState ->
                        when (imageState) {
                            is ImageLoadState.Success -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.25f)
                                        .fillMaxHeight()
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
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(text = list[index].title, fontSize = 18.sp)
                        Text(text = list[index].release_date, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
