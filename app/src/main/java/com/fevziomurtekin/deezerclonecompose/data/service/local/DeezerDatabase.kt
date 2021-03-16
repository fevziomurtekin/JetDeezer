package com.fevziomurtekin.deezerclonecompose.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fevziomurtekin.deezerclonecompose.core.DEEZER_DATABASE_VERSION
import com.fevziomurtekin.deezerclonecompose.di.RoomConverter
import com.fevziomurtekin.deezerclonecompose.data.entities.AlbumEntity
import com.fevziomurtekin.deezerclonecompose.data.entities.GenreEntity
import com.fevziomurtekin.deezerclonecompose.data.entities.SearchEntity

@Database(entities = [GenreEntity::class, SearchEntity::class, AlbumEntity::class],
    version = DEEZER_DATABASE_VERSION, exportSchema = false)
@TypeConverters(value = [RoomConverter::class])
abstract class DeezerDatabase : RoomDatabase(){
    abstract fun deezerDao() : DeezerDao
}