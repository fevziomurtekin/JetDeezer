package com.fevziomurtekin.deezerclonecompose.data.response.genre


data class GenreResponse(
        val data: List<Genre>
)

data class Genre(
        val id: String,
        val name: String,
        val picture: String,
        val picture_medium: String,
        val type: String
)