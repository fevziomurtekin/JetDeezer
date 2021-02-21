package com.fevziomurtekin.deezerclonecompose.data.datasource

import com.fevziomurtekin.deezerclonecompose.core.utils.letOnFalse
import com.fevziomurtekin.deezerclonecompose.core.utils.letOnTrue
import com.fevziomurtekin.deezerclonecompose.data.DeezerDaoResult
import com.fevziomurtekin.deezerclonecompose.data.DeezerResult
import com.fevziomurtekin.deezerclonecompose.data.isSuccessAndNotNull
import retrofit2.Response
import java.io.IOException

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


@JvmName("selectData")
suspend fun <T: Any> localCallFetch(
    call: suspend () -> List<T>?
) : DeezerDaoResult {
    var localReturn = DeezerDaoResult(isSucces = true,null)
    try {
        val response = call.invoke()
        response.isNullOrEmpty().letOnFalse {
            localReturn = DeezerDaoResult(true, response)
        }.letOnTrue {
            localReturn = DeezerDaoResult(false, Exception("Unexpected error in Dao"))
        }
    }catch (e:Exception){
        DeezerDaoResult(false,e)
    }
    return localReturn
}

/**
 * insert to data in Dao.
 */
@JvmName("insertData")
suspend fun localCallInsert(
    call: suspend () -> Unit
) : DeezerDaoResult {
    var localReturn = DeezerDaoResult(true,null)
    localReturn = try {
        call.invoke()
        DeezerDaoResult(true, "Data addition has been done succesfully...")
    }catch (e:Exception){
        DeezerDaoResult(false,e)
    }
    return localReturn
}

