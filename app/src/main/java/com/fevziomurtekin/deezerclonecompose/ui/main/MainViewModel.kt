package com.fevziomurtekin.deezerclonecompose.ui.main

import android.accounts.NetworkErrorException
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.repository.GenreRepository
import com.fevziomurtekin.deezerclonecompose.data.response.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SPLASH_SCREEN_DELAY = 2000L

@HiltViewModel
class MainViewModel @Inject constructor(
    private val genreRepository: GenreRepository
): ViewModel(){

    private var _genreState: MutableState<DeezerResult<List<Genre>?>>
        = mutableStateOf(DeezerResult.Loading)

    @VisibleForTesting
    val genreState: State<DeezerResult<List<Genre>?>>
        get() = _genreState

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
}