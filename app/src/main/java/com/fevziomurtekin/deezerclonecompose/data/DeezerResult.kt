package com.fevziomurtekin.deezerclonecompose.data

sealed class DeezerResult<out T>{
    data class Success<out T>(val data:T) : DeezerResult<T>()
    object Loading : DeezerResult<Nothing>()
    data class Error(val exception: Exception) : DeezerResult<Nothing>()
}