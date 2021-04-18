package com.fevziomurtekin.deezerclonecompose.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fevziomurtekin.deezerclonecompose.data.repository.GenreRepository
import com.fevziomurtekin.deezerclonecompose.data.response.AlbumData
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistData
import com.fevziomurtekin.deezerclonecompose.data.response.Genre
import com.fevziomurtekin.deezerclonecompose.main.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: fevziomurtekin
 * @date: 17/04/2021
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val homeRepository: HomeRepository
): ViewModel() {

    val state = MutableStateFlow(HomeViewState())
    private val _state = MutableStateFlow(HomeViewState())

    private val genreList = MutableStateFlow(listOf<Genre>())
    private val chartArtists = MutableStateFlow(listOf<ArtistData>())
    private val chartPodcasts = MutableStateFlow(listOf<ArtistData>())
    private val chartAlbums = MutableStateFlow(listOf<AlbumData>())

    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadData() {
       viewModelScope.launch {

           /**
            * Returns a Flow whose values are generated with transform function
            * by combining the most recently emitted values by each flow.
            */

           combine(
                    genreList.flatMapLatest { genreRepository.fetchGenreList() },
                    chartPodcasts.flatMapLatest { homeRepository.fetchChartPodcasts() },
                    chartArtists.flatMapLatest { homeRepository.fetchChartArtists() },
                    chartAlbums.flatMapLatest { homeRepository.fetchChartAlbums() }
            ) { genres, podcasts, artists, albums ->
                state.value.copy(
                    genres = genres,
                    podcasts = podcasts,
                    artists = artists,
                    albums = albums
                )
            }.collect { _state.value = it }
        }
    }
}
