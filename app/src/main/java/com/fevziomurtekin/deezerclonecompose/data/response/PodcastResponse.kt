package com.fevziomurtekin.deezerclonecompose.data.response

import com.squareup.moshi.Json

/**
 * @author: fevziomurtekin
 * @date: 18/04/2021
 */

data class PodcastResponse(
        @field:Json(name = "data") val data: List<Podcast>,
        @field:Json(name = "total") val total: Int
)

data class Podcast(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "title") val title: String,
        @field:Json(name = "description") val description: String?,
        @field:Json(name = "available") val available: Boolean,
        @field:Json(name = "fans") val fans: Int,
        @field:Json(name = "link") val link: String,
        @field:Json(name = "share") val share: String,
        @field:Json(name = "picture") val picture: String,
        @field:Json(name = "type") val type: String
)