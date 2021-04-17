package com.fevziomurtekin.deezerclonecompose.data.response

import com.squareup.moshi.Json

data class ListResponse(
        @field:Json(name = "data") val data: List<ListData>
)

data class ListData(
        @field:Json(name = "id") val id: String,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "picture") val picture: String,
        @field:Json(name = "picture_medium") val picture_medium: String,
        @field:Json(name = "type") val type: String
)