package com.fevziomurtekin.deezerclonecompose.di

import androidx.room.TypeConverter
import androidx.room.ProvidedTypeConverter
import com.fevziomurtekin.deezerclonecompose.data.entities.AlbumEntity
import com.fevziomurtekin.deezerclonecompose.data.entities.GenreEntity
import com.fevziomurtekin.deezerclonecompose.data.entities.SearchEntity
import com.squareup.moshi.Moshi
import javax.inject.Inject

@ProvidedTypeConverter
class RoomConverter @Inject constructor(
   val moshi: Moshi
) {

    /* --------------------------  Decode Entity Class --------------------------------*/

    @TypeConverter
    fun fromStringToGenreEntity(value:String): GenreEntity?
        = moshi.adapter(GenreEntity::class.java).fromJson(value)


    @TypeConverter
    fun fromStringToSearchEntity(value:String): SearchEntity?
        = moshi.adapter(SearchEntity::class.java).fromJson(value)

    @TypeConverter
    fun fromStringToAlbumEntity(value:String): AlbumEntity?
        = moshi.adapter(AlbumEntity::class.java).fromJson(value)



    /* --------------------------------  Encode String ----------------------------------*/

    @TypeConverter
    fun fromGenreEntityToString(value:GenreEntity):String
        = moshi.adapter(GenreEntity::class.java).toJson(value)

    @TypeConverter
    fun fromSearchEntityToString(value: SearchEntity):String
        = moshi.adapter(SearchEntity::class.java).toJson(value)

    @TypeConverter
    fun fromAlbumEntityToString(value: AlbumEntity):String
        = moshi.adapter(AlbumEntity::class.java).toJson(value)

}