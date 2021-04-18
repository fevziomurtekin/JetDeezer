package com.fevziomurtekin.deezerclonecompose.data.repository

import com.fevziomurtekin.deezerclonecompose.core.utils.letOnTrueOnSuspend
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.datasource.remoteCall
import com.fevziomurtekin.deezerclonecompose.data.getResult
import com.fevziomurtekin.deezerclonecompose.data.isSuccessAndNotNull
import com.fevziomurtekin.deezerclonecompose.data.response.Genre
import com.fevziomurtekin.deezerclonecompose.data.response.GenreResponse
import com.fevziomurtekin.deezerclonecompose.data.service.local.DeezerDao
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import com.fevziomurtekin.deezerclonecompose.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    val deezerClient: DeezerClient,
    val deezerDao: DeezerDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): GenreRepository {
    override suspend fun fetchGenreList(): Flow<DeezerResult<List<Genre>?>> = flow {
        remoteCall {
            deezerClient.fetchGenreList()
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                (apiResult.getResult() as? GenreResponse)?.data
                    ?.let {
                        emit(DeezerResult.Success(it))
                    } ?: run {
                    emit(DeezerResult.Error(TypeCastException("unknown error.")))
                }
            }
        }
    }.catch { e->
        emit(DeezerResult.Error(Exception(e)))
    }.flowOn(ioDispatcher)

}

interface GenreRepository {
    /**
     * give to id return fetching genreList list.
     * @return Result.Error or Result.Success(List<Data>)
     * */
    suspend fun fetchGenreList(): Flow<DeezerResult<List<Genre>?>>
}