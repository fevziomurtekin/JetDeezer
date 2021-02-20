package com.fevziomurtekin.deezerclonecompose.data.datasource

import com.fevziomurtekin.deezerclonecompose.core.utils.letOnFalse
import com.fevziomurtekin.deezerclonecompose.core.utils.letOnTrue
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.isSuccessAndNotNull
import retrofit2.Response
import java.io.IOException

open class DeezerDataSource {

    suspend fun <T :Any> networkCall(
            call: suspend () -> Response<T>
    ) : DeezerResult<T?> {
        var networkReturn:DeezerResult<T?> = DeezerResult.Loading
        try {
            val response = call.invoke()
            response.isSuccessAndNotNull().letOnTrue{
                networkReturn = DeezerResult.Success(response.body())
            }.letOnFalse{
                networkReturn = DeezerResult.Error(IOException(response.errorBody()?.string().orEmpty()))
            }
        } catch (e: Exception) {
            networkReturn = DeezerResult.Error(e)
        }
        return networkReturn
    }



}