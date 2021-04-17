package com.fevziomurtekin.deezerclonecompose.di

import com.fevziomurtekin.deezerclonecompose.data.repository.*
import com.fevziomurtekin.deezerclonecompose.data.service.local.DeezerDao
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideGenreRepository(
        genreRepositoryImpl: GenreRepositoryImpl
    ): GenreRepository

    @Binds
    abstract fun provideArtistRepository(
        artistRepositoryImpl: ArtistRepositoryImpl
    ): ArtistRepository

    @Binds
    abstract fun provideAlbumRepository(
        albumRepositoryImpl: AlbumRepositoryImpl
    ): AlbumRepository

}