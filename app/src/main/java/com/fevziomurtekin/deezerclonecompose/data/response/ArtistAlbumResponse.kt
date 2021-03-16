package com.fevziomurtekin.deezerclonecompose.data.response

import com.squareup.moshi.Json

data class ArtistAlbumResponse(
    @field:Json(name = "data") val `data`: List<ArtistAlbumData>,
    @field:Json(name = "next") val next: String="",
    @field:Json(name = "total") val total: Int
)

data class ArtistAlbumData(
    @field:Json(name = "cover") val cover: String="",
    @field:Json(name = "cover_big") val cover_big: String="",
    @field:Json(name = "cover_medium") val cover_medium: String="",
    @field:Json(name = "cover_small") val cover_small: String="",
    @field:Json(name = "cover_xl") val cover_xl: String="",
    @field:Json(name = "explicit_lyrics") val explicit_lyrics: Boolean?=false,
    @field:Json(name = "fans") val fans: Int=0,
    @field:Json(name = "genre_id") val genre_id: Int=0,
    @field:Json(name = "id") val id: String="",
    @field:Json(name = "link") val link: String="",
    @field:Json(name = "md5_image") val md5_image: String="",
    @field:Json(name = "record_type") val record_type: String="",
    @field:Json(name = "release_date") val release_date: String="",
    @field:Json(name = "title") val title: String="",
    @field:Json(name = "tracklist") val tracklist: String="",
    @field:Json(name = "type") val type: String=""
)