package com.fevziomurtekin.deezerclonecompose.data.repository

import com.fevziomurtekin.deezerclonecompose.core.utils.letOnFalseOnSuspend
import com.fevziomurtekin.deezerclonecompose.core.utils.letOnTrueOnSuspend
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.datasource.networkCall
import com.fevziomurtekin.deezerclonecompose.data.getResult
import com.fevziomurtekin.deezerclonecompose.data.isSuccessAndNotNull
import com.fevziomurtekin.deezerclonecompose.data.response.AlbumDetailsResponse
import com.fevziomurtekin.deezerclonecompose.data.response.ArtistsResponse
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


private const val FAKE_DELAY_TIME = 1500L

class AlbumRepository(
    private val deezerClient: DeezerClient
): AlbumRepositoryImpl {
    override fun fetchAlbumDetails(albumID: String)
    = flow {
        networkCall {
            deezerClient.fetchAlbumDetails(albumID)
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                emit(
                    DeezerResult.Success(
                        (apiResult.getResult() as AlbumDetailsResponse)
                            .data
                    )
                )
            }.letOnFalseOnSuspend {
                delay(FAKE_DELAY_TIME)
                emit(DeezerResult.Error(Exception("Unexpected error.")))
            }
        }
    }.flowOn(Dispatchers.IO)


}

interface AlbumRepositoryImpl {
    /**
     * give to id return fetching artist list.
     * @param albumID, String
     * @return Result.Error or Result.Success(List<AlbumData>)
     * */
    fun fetchAlbumDetails(albumID:String): Flow<DeezerResult<*>>


}