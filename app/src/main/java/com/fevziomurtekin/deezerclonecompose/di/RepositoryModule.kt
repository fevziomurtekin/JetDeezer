package com.fevziomurtekin.deezerclonecompose.di

import com.fevziomurtekin.deezerclonecompose.data.repository.GenreRepository
import com.fevziomurtekin.deezerclonecompose.data.service.local.DeezerDao
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideGenreRepository(
        deezerClient: DeezerClient,
        deezerDao: DeezerDao
    ): GenreRepository = GenreRepository(
        deezerClient = deezerClient,
        deezerDao = deezerDao
    )

}