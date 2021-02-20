package com.fevziomurtekin.deezerclonecompose.data

import retrofit2.Response

sealed class DeezerResult<out T>{
    data class Success<out T>(val data:T) : DeezerResult<T>()
    object Loading : DeezerResult<Nothing>()
    data class Error(val exception: Exception) : DeezerResult<Nothing>()
}

data class DeezerDaoResult(
        val isSucces: Boolean,
        val data: Any?
)

fun Response<*>?.isSuccessAndNotNull():Boolean = this?.let {
    it.body() != null && it.isSuccessful
} ?: run {
    false
}

fun DeezerResult<*>?.isSuccessAndNotNull() =
        this is DeezerResult.Success && this.data != null


fun DeezerDaoResult?.isSuccessAndNotNull() = this?.let {
    it.data != null && it.isSucces
} ?: run {
    false
}

fun DeezerResult<*>?.getResult(): Any? =  when(this) {
    is DeezerResult.Success -> this.data
    else -> null
}