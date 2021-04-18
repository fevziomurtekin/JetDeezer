package com.fevziomurtekin.deezerclonecompose.home

import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.response.AlbumData
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistData
import com.fevziomurtekin.deezerclonecompose.data.response.Genre

/**
 * @author: fevziomurtekin
 * @date: 18/04/2021
 */

data class HomeViewState(
    var genres: DeezerResult<List<Genre>?>
        = DeezerResult.Loading,
    var podcasts: DeezerResult<List<ArtistData>>
        = DeezerResult.Loading,
    var artists: DeezerResult<List<ArtistData>>
        = DeezerResult.Loading,
    var albums: DeezerResult<List<AlbumData>>
        = DeezerResult.Loading,
    var isRefresh:Boolean = false
)