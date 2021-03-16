package com.fevziomurtekin.deezerclonecompose.data.response

import com.squareup.moshi.Json


data class AlbumDetailsResponse(
    @field:Json(name = "data")
    val `data`: List<AlbumData>,
    @field:Json(name = "total")
    val total: Int
)

data class AlbumData(
    @field:Json(name = "disk_number") val disk_number: Int,
    @field:Json(name = "duration") var duration: String,
    @field:Json(name = "explicit_content_cover") val explicit_content_cover: Int,
    @field:Json(name = "explicit_content_lyrics") val explicit_content_lyrics: Int,
    @field:Json(name = "explicit_lyrics") val explicit_lyrics: Boolean,
    @field:Json(name = "id") var id: String,
    @field:Json(name = "isrc") val isrc: String="",
    @field:Json(name = "link") val link: String?="",
    @field:Json(name = "md5_image") val md5_image: String?="",
    @field:Json(name = "preview") val preview: String?="",
    @field:Json(name = "rank") val rank: String?="",
    @field:Json(name = "readable") val readable: Boolean,
    @field:Json(name = "title") val title: String?="",
    @field:Json(name = "title_short") val title_short: String?="",
    @field:Json(name = "title_version") val title_version: String?="",
    @field:Json(name = "track_position") val track_position: Int,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "fav_time") var fav_time:Long=System.currentTimeMillis(),
    @field:Json(name = "def_img") val def_img:String
    ="https://www.iconfinder.com/data/icons/social-media-circle-flat-1/1024/itunes-01-01-64.png"
){
    fun durationToTime(){
        val minute = (duration.toInt()) / 60
        val second = (duration.toInt()) % 60
        val sMin = if(minute<10) "0$minute" else minute.toString()
        duration = "$sMin:$second"
    }
}


data class Artist(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "link") val link: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "picture") val picture: String,
    @field:Json(name = "picture_big") val picture_big: String,
    @field:Json(name = "picture_medium") val picture_medium: String,
    @field:Json(name = "picture_small") val picture_small: String,
    @field:Json(name = "picture_xl") val picture_xl: String,
    @field:Json(name = "tracklist") val tracklist: String,
    @field:Json(name = "type") val type: String
)