package com.fevziomurtekin.deezerclonecompose.ui.util


// TODO It'll new actions.
object Screens {

    const val HOME = "DeezerHome"
    const val DETAILS = "DeezerGenre/{${ScreenArguments.GENRE_ID}}"
    const val ARTIST = "DeezerArtist/{${ScreenArguments.ARTIST_ID}}"
    const val ALBUM = "DeezerAlbum/{${ScreenArguments.ALBUM_ID}}"

    fun createGenreDirections(id: String) = "DeezerGenre/$id"
    fun createArtistsDirections(artistId: String) = "DeezerArtist/$artistId"
    fun createAlbumsDetails(albumId:String) = "DeezerAlbum/$albumId"
}
