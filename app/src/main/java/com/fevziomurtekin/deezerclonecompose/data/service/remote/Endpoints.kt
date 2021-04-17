package com.fevziomurtekin.deezerclonecompose.data.service.remote


object Endpoints {
    const val GENRE = "/genre"
    const val RADIO = "/radio"
    const val CHART_ARTIST = "/chart/0/artists"
    const val CHART_PODCAST = "/chart/0/podcast"
    const val ARTISTS = "genre/{genreId}/artists"
    const val ARTIST = "artist/{artistId}"
    const val ALBUMS = "artist/{artistId}/albums"
    const val RELATED = "artist/{artistId}/related"
    const val ALBUM = "album/{albumId}/tracks"
    const val SEARCH = "search/album"
}