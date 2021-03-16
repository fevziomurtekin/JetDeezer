package com.fevziomurtekin.deezerclonecompose.ui.main

import android.accounts.NetworkErrorException
import androidx.annotation.VisibleForTesting
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.repository.GenreRepository
import com.fevziomurtekin.deezerclonecompose.data.response.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SPLASH_SCREEN_DELAY = 2000L

@HiltViewModel
class MainViewModel @Inject constructor(
    private val genreRepository: GenreRepository
): ViewModel(){

    private var _genreLiveData: Flow<DeezerResult<List<Genre>?>>
        = MutableStateFlow(DeezerResult.Loading)

    @VisibleForTesting
    val genreListLiveData: Flow<DeezerResult<List<Genre>?>>
        get() = _genreLiveData

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
                _genreLiveData = genreRepository.fetchGenreList()
            }catch (e: NetworkErrorException){
                isNetworkError.value = true
            }

        }
    }
}