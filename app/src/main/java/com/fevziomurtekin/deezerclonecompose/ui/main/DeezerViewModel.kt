package com.fevziomurtekin.deezerclonecompose.ui.main

import android.accounts.NetworkErrorException
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.repository.AlbumRepository
import com.fevziomurtekin.deezerclonecompose.data.repository.ArtistRepository
import com.fevziomurtekin.deezerclonecompose.data.repository.GenreRepository
import com.fevziomurtekin.deezerclonecompose.data.response.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SPLASH_SCREEN_DELAY = 2000L

@HiltViewModel
class DeezerViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val artistRepository: ArtistRepository,
    private val albumRepository: AlbumRepository
): ViewModel(){

    private var _genreState: MutableState<DeezerResult<List<Genre>?>>
        = mutableStateOf(DeezerResult.Loading)
    @VisibleForTesting
    val genreState: State<DeezerResult<List<Genre>?>>
        get() = _genreState

    private var _artistsState: MutableState<DeezerResult<List<ArtistData>?>>
            = mutableStateOf(DeezerResult.Loading)
    @VisibleForTesting
    val artistsState: State<DeezerResult<List<ArtistData>?>>
        get() = _artistsState

    private var _artistDetailState: MutableState<DeezerResult<ArtistDetailResponse>>
            = mutableStateOf(DeezerResult.Loading)
    @VisibleForTesting
    val artistDetailState: State<DeezerResult<ArtistDetailResponse>>
        get() = _artistDetailState

    private var _artistAlbumsState: MutableState<DeezerResult<ArtistAlbumResponse>>
            = mutableStateOf(DeezerResult.Loading)
    @VisibleForTesting
    val artistAlbumsState: State<DeezerResult<ArtistAlbumResponse>>
        get() = _artistAlbumsState

    private var _albumDetailsState: MutableState<DeezerResult<List<AlbumData>>>
            = mutableStateOf(DeezerResult.Loading)
    @VisibleForTesting
    val albumDetailsState: State<DeezerResult<List<AlbumData>>>
        get() = _albumDetailsState


    private var _splashShown = MutableLiveData(true)
    val splashShown get() = _splashShown

    val isNetworkError = MutableLiveData(false)

    fun showSplashScreen() {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_DELAY)
            _splashShown.value = false
        }
    }

    fun fetchGenreList(){
        viewModelScope.launch {
            try {
                genreRepository
                        .fetchGenreList()
                        .collect {
                            _genreState.value = it
                        }
            }catch (e: NetworkErrorException){
                isNetworkError.value = true
            }

        }
    }

    fun fetchArtists(genreID: String) {
        viewModelScope.launch {
            try {
                artistRepository
                        .fetchArtistList(genreID = genreID)
                        .collect { artist->
                            _artistsState.value = artist
                        }
            }catch (e: NetworkErrorException){
                isNetworkError.value = true
            }

        }
    }

    fun fetchArtistDetails(artistID: String) {
        viewModelScope.launch {
            try {
                artistRepository
                    .fetchArtistDetails(artistID = artistID)
                    .collect { artistDetails->
                        _artistDetailState.value = artistDetails
                    }
            }catch (e: NetworkErrorException){
                isNetworkError.value = true
            }
        }
    }

    fun fetchArtistAlbums(artistID: String) {
        viewModelScope.launch {
            try {
                artistRepository
                        .fetchArtistAlbums(artistID = artistID)
                        .collect { artistAlbums->
                            _artistAlbumsState.value = artistAlbums
                        }
            }catch (e: NetworkErrorException){
                isNetworkError.value = true
            }
        }
    }

    fun fetchAlbumDetails(albumID: String) {
        viewModelScope.launch {
            try {
                albumRepository
                    .fetchAlbumDetails(albumID = albumID)
                    .collect { albumDetails ->
                        _albumDetailsState.value = albumDetails
                    }
            }catch (e: NetworkErrorException){
                isNetworkError.value = true
            }
        }
    }
}