package com.fevziomurtekin.deezerclonecompose.ui.main

import android.accounts.NetworkErrorException
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.repository.ArtistRepository
import com.fevziomurtekin.deezerclonecompose.data.repository.GenreRepository
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistData
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistDetailResponse
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistsResponse
import com.fevziomurtekin.deezerclonecompose.data.response.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SPLASH_SCREEN_DELAY = 2000L

@HiltViewModel
class DeezerViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val artistRepository: ArtistRepository
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
}