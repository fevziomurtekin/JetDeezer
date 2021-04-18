package com.fevziomurtekin.deezerclonecompose.data.repository

import com.fevziomurtekin.deezerclonecompose.core.utils.letOnFalseOnSuspend
import com.fevziomurtekin.deezerclonecompose.core.utils.letOnTrueOnSuspend
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.datasource.remoteCall
import com.fevziomurtekin.deezerclonecompose.data.isSuccessAndNotNull
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistsResponse
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import com.fevziomurtekin.deezerclonecompose.data.getResult
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistAlbumResponse
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistDetailResponse
import com.fevziomurtekin.deezerclonecompose.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


private const val FAKE_DELAY_TIME = 1500L

class ArtistRepositoryImpl @Inject constructor(
    private val deezerClient: DeezerClient,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): ArtistRepository {

    override fun fetchArtistList(genreID:String)
    = flow {
        remoteCall {
            deezerClient.fetchArtistList(genreID)
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                emit(
                        DeezerResult.Success(
                                (apiResult.getResult() as ArtistsResponse)
                                        .data
                        )
                )
            }.letOnFalseOnSuspend {
                delay(FAKE_DELAY_TIME)
                emit(DeezerResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(ioDispatcher)

    override fun fetchArtistDetails(artistID:String)
    = flow {
        remoteCall {
            deezerClient.fetchArtistDetails(artistID)
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                emit(DeezerResult.Success(apiResult.getResult() as ArtistDetailResponse))
            }.letOnFalseOnSuspend {
                delay(FAKE_DELAY_TIME)
                emit(DeezerResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(ioDispatcher)


    override fun fetchArtistAlbums(artistID: String)
    = flow {
        remoteCall {
            deezerClient.fetchArtistAlbums(artistID)
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                emit(DeezerResult.Success(apiResult.getResult() as ArtistAlbumResponse))
            }.letOnFalseOnSuspend {
                delay(FAKE_DELAY_TIME)
                emit(DeezerResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(ioDispatcher)

}


interface ArtistRepository {
    /**
     * give to id return fetching artist list.
     * @param genreID, String
     * @return Result.Error or Result.Succes(List<ArtistData>)
     * */
    fun fetchArtistList(genreID:String): Flow<DeezerResult<*>>

    fun fetchArtistDetails(artistID: String): Flow<DeezerResult<*>>

    fun fetchArtistAlbums(artistID: String): Flow<DeezerResult<*>>

}