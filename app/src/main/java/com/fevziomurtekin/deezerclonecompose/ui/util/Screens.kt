package com.fevziomurtekin.deezerclonecompose.ui.util


// TODO It'll new actions.
object Screens {

    const val HOME = "DeezerHome"
    const val DETAILS = "DeezerGenre/{${ScreenArguments.GENRE_ID}}"
    const val ARTIST = "DeezerArtist"

    fun createGenreDirections(id: String) = "DeezerGenre/$id"
}
