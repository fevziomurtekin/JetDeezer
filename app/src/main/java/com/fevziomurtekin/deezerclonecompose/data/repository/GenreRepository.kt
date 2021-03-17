package com.fevziomurtekin.deezerclonecompose.data.repository

import com.fevziomurtekin.deezerclonecompose.core.mapper.mapper
import com.fevziomurtekin.deezerclonecompose.core.utils.letOnFalseOnSuspend
import com.fevziomurtekin.deezerclonecompose.core.utils.letOnTrueOnSuspend
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.datasource.localCallFetch
import com.fevziomurtekin.deezerclonecompose.data.datasource.localCallInsert
import com.fevziomurtekin.deezerclonecompose.data.datasource.networkCall
import com.fevziomurtekin.deezerclonecompose.data.entities.GenreEntity
import com.fevziomurtekin.deezerclonecompose.data.getResult
import com.fevziomurtekin.deezerclonecompose.data.isSuccessAndNotNull
import com.fevziomurtekin.deezerclonecompose.data.response.Genre
import com.fevziomurtekin.deezerclonecompose.data.response.GenreResponse
import com.fevziomurtekin.deezerclonecompose.data.service.local.DeezerDao
import com.fevziomurtekin.deezerclonecompose.data.service.remote.DeezerClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenreRepository @Inject constructor(
    val deezerClient: DeezerClient,
    val deezerDao: DeezerDao
): GenreRepositoryImpl {
    override suspend fun fetchGenreList(): Flow<DeezerResult<List<Genre>?>> = flow {
        localCallFetch {
            deezerDao.getGenreList()
        }.let { localResult ->
            localResult.isSucces.letOnFalseOnSuspend {
                networkCall {
                    deezerClient.fetchGenreList()
                }.let { apiResult->
                    apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                        (apiResult.getResult() as? GenreResponse)?.data?.let {
                            localCallInsert { deezerDao.insertGenreList(it.mapper()) }
                            emit(DeezerResult.Success(it))
                        } ?: run {
                            emit(DeezerResult.Error(TypeCastException("unkown error.")))
                        }
                    }
                }
            }.letOnTrueOnSuspend {
                val result = (localResult.data as? List<GenreEntity>)?.mapper()
                delay(1500)
                emit(DeezerResult.Success(result?.toList()))
            }
        }
    }.flowOn(Dispatchers.IO)

}

interface GenreRepositoryImpl {
    /**
     * give to id return fetching genreList list.
     * @return Result.Error or Result.Succes(List<Data>)
     * */
    suspend fun fetchGenreList(): Flow<DeezerResult<List<Genre>?>>
}