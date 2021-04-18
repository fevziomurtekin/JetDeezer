package com.fevziomurtekin.deezerclonecompose.home

import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.response.*

/**
 * @author: fevziomurtekin
 * @date: 18/04/2021
 */

data class HomeViewState(
    var genres: DeezerResult<List<Genre>?>
        = DeezerResult.Loading,
    var podcasts: DeezerResult<List<Podcast>>
        = DeezerResult.Loading,
    var artists: DeezerResult<List<ArtistData>>
        = DeezerResult.Loading,
    var albums: DeezerResult<List<Album>>
        = DeezerResult.Loading,
    var isRefresh:Boolean = false
)