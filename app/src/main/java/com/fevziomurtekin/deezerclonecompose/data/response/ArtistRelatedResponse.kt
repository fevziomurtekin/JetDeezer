package com.fevziomurtekin.deezerclonecompose.data.response

import com.squareup.moshi.Json

data class ArtistRelatedResponse(
    @field:Json(name = "data") val data: List<ArtistRelatedData>,
    @field:Json(name = "total") val total: Int
)

data class ArtistRelatedData(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "link") val link: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "nb_album") val nb_album: Int,
    @field:Json(name = "nb_fan") val nb_fan: Int,
    @field:Json(name = "picture") val picture: String,
    @field:Json(name = "picture_big") val picture_big: String,
    @field:Json(name = "picture_medium") val picture_medium: String,
    @field:Json(name = "picture_small") val picture_small: String,
    @field:Json(name = "picture_xl") val picture_xl: String,
    @field:Json(name = "radio") val radio: Boolean,
    @field:Json(name = "tracklist") val tracklist: String,
    @field:Json(name = "type") val type: String
)