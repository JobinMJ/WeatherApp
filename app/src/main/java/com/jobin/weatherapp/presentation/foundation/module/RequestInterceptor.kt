package com.jobin.weatherapp.presentation.foundation.module

import com.jobin.weatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter("appid", BuildConfig.APPID)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}