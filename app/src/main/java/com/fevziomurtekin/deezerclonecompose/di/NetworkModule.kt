package com.fevziomurtekin.deezerclonecompose.di

import com.fevziomurtekin.deezerclonecompose.BuildConfig
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerInterceptor
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient
            = OkHttpClient.Builder()
        .addInterceptor(DeezerInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.DEEZER_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()))
        .build()

    @Provides
    @Singleton
    fun provideDeezerService(retrofit:Retrofit) = retrofit.create(DeezerService::class.java)

    @Provides
    @Singleton
    fun provideDeezerClient(deezerService: DeezerService) = DeezerClient(deezerService)
}