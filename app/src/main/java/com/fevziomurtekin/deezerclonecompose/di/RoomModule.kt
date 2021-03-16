package com.fevziomurtekin.deezerclonecompose.di

import android.app.Application
import androidx.room.Room
import com.fevziomurtekin.deezerclonecompose.BuildConfig
import com.fevziomurtekin.deezerclonecompose.data.service.local.DeezerDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule{

    @Provides
    @Singleton
    fun provideMoshi(): Moshi
            = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideRoomConverter(moshi: Moshi): RoomConverter
        = RoomConverter(moshi)

    @Provides
    @Singleton
    fun providesDeezerDatabase(application: Application)
            = Room.databaseBuilder(application, DeezerDatabase::class.java, BuildConfig.DEEZER_DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesDeezerDao(deezerDatabase: DeezerDatabase)
            = deezerDatabase.deezerDao()
}