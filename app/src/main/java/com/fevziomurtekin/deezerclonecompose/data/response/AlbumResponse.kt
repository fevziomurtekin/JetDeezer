package com.fevziomurtekin.deezerclonecompose.data.response

import com.squareup.moshi.Json

/**
 * @author: fevziomurtekin
 * @date: 18/04/2021
 */

data class AlbumResponse(
        @field:Json(name = "data") val data: List<Album>,
        @field:Json(name = "total") val total: Int
)

data class Album(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "title") val title: String,
        @field:Json(name = "cover") val cover: String?,
        @field:Json(name = "available") val available: Boolean,
        @field:Json(name = "explicit_lyrics") val explicit_lyrics: Boolean,
        @field:Json(name = "md5_image") val md5_image: String,
        @field:Json(name = "record_type") val record_type: String,
        @field:Json(name = "tracklist") val tracklist: String,
        @field:Json(name = "position") val position: Int,
        @field:Json(name = "artist") val artist: List<ArtistData>,
        @field:Json(name = "type") val type: String,
)