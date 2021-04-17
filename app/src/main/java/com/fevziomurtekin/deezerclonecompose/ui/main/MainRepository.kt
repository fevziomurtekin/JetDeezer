package com.fevziomurtekin.deezerclonecompose.ui.main

import com.fevziomurtekin.deezerclonecompose.core.utils.letOnFalseOnSuspend
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.datasource.remoteCall
import com.fevziomurtekin.deezerclonecompose.data.getResult
import com.fevziomurtekin.deezerclonecompose.data.isSuccessAndNotNull
import com.fevziomurtekin.deezerclonecompose.data.response.*
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import com.fevziomurtekin.deezerclonecompose.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author: fevziomurtekin
 * @date: 17/04/2021
 */

abstract class MainRepositoryImpl @Inject constructor(
    private val deezerClient: DeezerClient,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): MainRepository {

    override suspend fun fetchChartAlbums(): Flow<DeezerResult<List<AlbumData>>> = flow {
        remoteCall {
            deezerClient.fetchChartAlbums()
        }.let { apiResult->
            apiResult.isSuccessAndNotNull().letOnFalseOnSuspend {
                (apiResult.getResult() as? AlbumDetailsResponse)
                    ?.data?.let {
                        emit(DeezerResult.Success(it))
                    } ?: run {
                    emit(DeezerResult.Error(TypeCastException("unkown error.")))
                }
            }
        }
    }.flowOn(ioDispatcher)

    override suspend fun fetchChartArtists(): Flow<DeezerResult<List<ArtistData>>> = flow {
        remoteCall {
            deezerClient.fetchChartArtists()
        }.let { apiResult->
            apiResult.isSuccessAndNotNull().letOnFalseOnSuspend {
                (apiResult.getResult() as? ArtistsResponse)
                    ?.data?.let {
                        emit(DeezerResult.Success(it))
                    } ?: run {
                    emit(DeezerResult.Error(TypeCastException("unkown error.")))
                }
            }
        }
    }.flowOn(ioDispatcher)

    override suspend fun fetchChartPodcasts(): Flow<DeezerResult<List<ArtistData>>> = flow {
        remoteCall {
            deezerClient.fetchChartPodcasts()
        }.let { apiResult->
            apiResult.isSuccessAndNotNull().letOnFalseOnSuspend {
                (apiResult.getResult() as? ArtistsResponse)
                    ?.data?.let {
                        emit(DeezerResult.Success(it))
                    } ?: run {
                    emit(DeezerResult.Error(TypeCastException("unkown error.")))
                }
            }
        }
    }.flowOn(ioDispatcher)

    override suspend fun fetchRadios(): Flow<DeezerResult<List<Genre>>> = flow {
        remoteCall {
            deezerClient.fetchRadios()
        }.let { apiResult->
            apiResult.isSuccessAndNotNull().letOnFalseOnSuspend {
                (apiResult.getResult() as? GenreResponse)
                    ?.data?.let {
                        emit(DeezerResult.Success(it))
                    } ?: run {
                    emit(DeezerResult.Error(TypeCastException("unkown error.")))
                }
            }
        }
    }.flowOn(ioDispatcher)

}

interface MainRepository  {

    suspend fun fetchRadios(): Flow<DeezerResult<List<Genre>>>

    suspend fun fetchChartArtists(): Flow<DeezerResult<List<ArtistData>>>

    suspend fun fetchChartPodcasts(): Flow<DeezerResult<List<ArtistData>>>

    suspend fun fetchChartAlbums(): Flow<DeezerResult<List<AlbumData>>>
}