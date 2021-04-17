package com.fevziomurtekin.deezerclonecompose.ui.main

import androidx.lifecycle.ViewModel
import com.fevziomurtekin.deezerclonecompose.data.repository.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author: fevziomurtekin
 * @date: 17/04/2021
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val mainRepository: MainRepository
): ViewModel() {

}