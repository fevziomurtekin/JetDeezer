package com.fevziomurtekin.deezerclonecompose.data.repository

import com.fevziomurtekin.deezerclonecompose.core.utils.letOnFalseOnSuspend
import com.fevziomurtekin.deezerclonecompose.core.utils.letOnTrueOnSuspend
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.datasource.networkCall
import com.fevziomurtekin.deezerclonecompose.data.isSuccessAndNotNull
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistsResponse
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import kotlinx.coroutines.Dispatchers
import com.fevziomurtekin.deezerclonecompose.data.getResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


private const val FAKE_DELAY_TIME = 1500L

class ArtistRepository(
        private val deezerClient: DeezerClient
): ArtistRepositoryImpl {

    override fun fetchArtistList(genreID:String)
            = flow {
        emit(DeezerResult.Loading)
        networkCall {
            deezerClient.fetchArtistList(genreID)
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                emit(
                        DeezerResult.Success(
                                (apiResult.getResult() as ArtistsResponse)
                                        .artistData
                        )
                )
            }.letOnFalseOnSuspend {
                delay(FAKE_DELAY_TIME)
                emit(DeezerResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(Dispatchers.IO)

}


interface ArtistRepositoryImpl {
    /**
     * give to id return fetching artist list.
     * @param genreID, String
     * @return Result.Error or Result.Succes(List<ArtistData>)
     * */
    fun fetchArtistList(genreID:String): Flow<DeezerResult<*>>

}