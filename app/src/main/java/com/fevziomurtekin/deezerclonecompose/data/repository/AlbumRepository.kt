package com.fevziomurtekin.deezerclonecompose.data.repository

import com.fevziomurtekin.deezerclonecompose.core.utils.letOnFalseOnSuspend
import com.fevziomurtekin.deezerclonecompose.core.utils.letOnTrueOnSuspend
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.datasource.remoteCall
import com.fevziomurtekin.deezerclonecompose.data.getResult
import com.fevziomurtekin.deezerclonecompose.data.isSuccessAndNotNull
import com.fevziomurtekin.deezerclonecompose.data.response.AlbumDetailsResponse
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import com.fevziomurtekin.deezerclonecompose.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


private const val FAKE_DELAY_TIME = 1500L

class AlbumRepositoryImpl @Inject constructor(
    private val deezerClient: DeezerClient,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): AlbumRepository {

    override fun fetchAlbumDetails(albumID: String)
    = flow {
        remoteCall {
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
    }.flowOn(ioDispatcher)


}

interface AlbumRepository {
    /**
     * give to id return fetching artist list.
     * @param albumID, String
     * @return Result.Error or Result.Success(List<AlbumData>)
     * */
    fun fetchAlbumDetails(albumID:String): Flow<DeezerResult<*>>


}