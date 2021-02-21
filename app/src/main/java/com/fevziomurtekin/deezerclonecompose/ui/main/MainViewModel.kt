package com.fevziomurtekin.deezerclonecompose.ui.main

import android.accounts.NetworkErrorException
import androidx.annotation.VisibleForTesting
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.repository.GenreRepository
import com.fevziomurtekin.deezerclonecompose.data.response.genre.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val genreRepository: GenreRepository
): ViewModel(){

    @VisibleForTesting
    var genreListLiveData: LiveData<DeezerResult<List<Genre>?>> = MutableLiveData()
    val isNetworkError = MutableLiveData(false)

    fun fetchGenreList(){
        viewModelScope.launch {
            try {
                genreListLiveData = genreRepository.fetchGenreList()
                    .asLiveData(viewModelScope.coroutineContext+ Dispatchers.Default)
            }catch (e: NetworkErrorException){
                isNetworkError.value = true
            }

        }
    }
}