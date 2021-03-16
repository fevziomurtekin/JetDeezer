package com.fevziomurtekin.deezerclonecompose.data.service.remote

import com.fevziomurtekin.deezerclonecompose.ui.DeezerDialog
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class DeezerInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
       //when(response.code()){
       //    in 400 .. 500 -> {
       //        // showErrorDialog()
       //    }
       //    else -> {
       //        // no proccess.
       //    }
       //}
        return response
    }

}