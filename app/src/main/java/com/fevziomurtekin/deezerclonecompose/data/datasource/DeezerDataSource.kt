package com.fevziomurtekin.deezerclonecompose.data.datasource

import com.fevziomurtekin.deezerclonecompose.core.utils.letOnFalse
import com.fevziomurtekin.deezerclonecompose.core.utils.letOnTrue
import com.fevziomurtekin.deezerclonecompose.data.DeezerDaoResult
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.isSuccessAndNotNull
import retrofit2.Response
import java.io.IOException

suspend fun <T :Any> remoteCall(
        call: suspend () -> Response<T>
) : DeezerResult<T?> {
    var result: DeezerResult<T?> = DeezerResult.Loading
    try {
        val response = call.invoke()
        response.isSuccessAndNotNull().letOnTrue{
            result = DeezerResult.Success(response.body())
        }.letOnFalse{
            result = DeezerResult.Error(IOException(response.errorBody()?.string().orEmpty()))
        }
    } catch (e: Exception) {
        result = DeezerResult.Error(e)
    }
    return result
}


suspend fun <T: Any> localCall(
    isInsert: Boolean = false,
    call: suspend () -> List<T>?
) : DeezerDaoResult {
    var result = DeezerDaoResult(isSucces = true,null)
    try {
        val response = call.invoke()
        response.isNullOrEmpty().letOnFalse {
            result = DeezerDaoResult(true, response)
        }.letOnTrue {
            result = DeezerDaoResult(false, Exception("Unexpected error in Dao"))
        }
    }catch (e: Exception){
        DeezerDaoResult(false,e)
    }
    return result
}

/**
 * insert to data in Dao.
 */
suspend fun localCallInsert(
    call: suspend () -> Unit
) : DeezerDaoResult {
    var result = DeezerDaoResult(true,null)
    result = try {
        call.invoke()
        DeezerDaoResult(true, "Data addition has been done succesfully...")
    }catch (e:Exception){
        DeezerDaoResult(false,e)
    }
    return result
}

