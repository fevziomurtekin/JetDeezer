package com.fevziomurtekin.deezerclonecompose.data.service.remote

import javax.inject.Inject

class DeezerClient @Inject constructor(
    private val deezerService: DeezerService
){

    suspend fun fetchGenreList()
            = deezerService.fetchGenreList()

    suspend fun fetchArtistList(genreId:String)
            = deezerService.fetchArtistList(genreId)

    suspend fun fetchArtistDetails(artistID: String)
            = deezerService.fetchArtistDetails(artistID)

    suspend fun fetchArtistAlbums(artistID: String)
            = deezerService.fetchArtistAlbums(artistID)

    suspend fun fetchArtistRelated(artistID: String)
            = deezerService.fetchArtistRelated(artistID)

    suspend fun fetchAlbumDetails(albumID:String)
            = deezerService.fetchAlbumDetails(albumID)

    suspend fun fetchSearchAlbum(q:String)
            = deezerService.fetchSearchAlbum(q)

    suspend fun fetchChartAlbums()
            = deezerService.fetchChartAlbums()

    suspend fun fetchRadios()
            = deezerService.fetchRadios()

    suspend fun fetchChartArtists()
            = deezerService.fetchChartArtists()

    suspend fun fetchChartPodcasts()
            = deezerService.fetchChartPodcasts()
}