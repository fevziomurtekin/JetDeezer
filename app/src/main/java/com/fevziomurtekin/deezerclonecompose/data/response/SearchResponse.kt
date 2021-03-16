package com.fevziomurtekin.deezerclonecompose.data.response

import com.squareup.moshi.Json

data class SearchResponse(
    @field:Json(name = "data") val `data`: List<SearchData>,
    @field:Json(name = "next") val next: String,
    @field:Json(name = "total") val total: Int
)

data class SearchData(
    @field:Json(name = "artist") var artist: Artist,
    @field:Json(name = "cover") val cover: String,
    @field:Json(name = "cover_big") val cover_big: String,
    @field:Json(name = "cover_medium") val cover_medium: String,
    @field:Json(name = "cover_small") val cover_small: String,
    @field:Json(name = "cover_xl") val cover_xl: String,
    @field:Json(name = "explicit_lyrics") val explicit_lyrics: Boolean,
    @field:Json(name = "genre_id") val genre_id: Int,
    @field:Json(name = "id") val id: String,
    @field:Json(name = "link") val link: String,
    @field:Json(name = "md5_image") val md5_image: String,
    @field:Json(name = "nb_tracks") val nb_tracks: Int,
    @field:Json(name = "record_type") val record_type: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "tracklist") val tracklist: String,
    @field:Json(name = "type") val type: String
)