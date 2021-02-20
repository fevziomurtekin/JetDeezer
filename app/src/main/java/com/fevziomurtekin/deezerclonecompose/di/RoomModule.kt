package com.fevziomurtekin.deezerclonecompose.di

import android.app.Application
import androidx.room.Room
import com.fevziomurtekin.deezerclonecompose.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule{

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