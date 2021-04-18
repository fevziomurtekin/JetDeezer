package com.fevziomurtekin.deezerclonecompose.di

import com.fevziomurtekin.deezerclonecompose.data.repository.*
import com.fevziomurtekin.deezerclonecompose.data.service.local.DeezerDao
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import com.fevziomurtekin.deezerclonecompose.main.HomeRepository
import com.fevziomurtekin.deezerclonecompose.main.HomeRepositoryImpl
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
    abstract fun bindsHomeRepository(
            homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    abstract fun bindsGenreRepository(
        genreRepositoryImpl: GenreRepositoryImpl
    ): GenreRepository

    @Binds
    abstract fun bindsArtistRepository(
        artistRepositoryImpl: ArtistRepositoryImpl
    ): ArtistRepository

    @Binds
    abstract fun bindsAlbumRepository(
        albumRepositoryImpl: AlbumRepositoryImpl
    ): AlbumRepository

}
