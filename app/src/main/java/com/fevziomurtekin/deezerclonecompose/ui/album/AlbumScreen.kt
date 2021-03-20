package com.fevziomurtekin.deezerclonecompose.ui.album

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.response.AlbumData
import com.fevziomurtekin.deezerclonecompose.ui.main.DeezerViewModel
import com.fevziomurtekin.deezerclonecompose.ui.util.CircularLoadingView
import com.fevziomurtekin.deezerclonecompose.ui.util.ErrorScreen
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState
import dev.chrisbanes.accompanist.imageloading.MaterialLoadingImage
import kotlinx.coroutines.launch


private const val DEF_ALBUM_NAME = "ALBUM"

@Composable
fun AlbumScreen(
    navController: NavHostController,
    viewModel: DeezerViewModel,
    albumID: Int
) {
    val scope = rememberCoroutineScope()
    viewModel.fetchAlbumDetails(albumID.toString())
    val albumDetailsState = viewModel.albumDetailsState
    when(val result = albumDetailsState.value) {
        is DeezerResult.Success -> {
            ArtistAlbums(
                result.data,
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
    list: List<AlbumData>,
    navController: NavHostController
) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = DEF_ALBUM_NAME,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = Modifier.padding(top = 8.dp)
        ){
            items(list.size){ index ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp))
                {
                    Row(modifier = Modifier
                        .fillMaxSize()){
                        CoilImage(data = list[index].def_img) { imageState ->
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
                            Text(text = list[index].title_short.orEmpty(), fontSize = 18.sp)
                            Text(text = list[index].rank.toString(), fontSize = 12.sp)
                        }
                    }
                }
            }
        }

    }
}
