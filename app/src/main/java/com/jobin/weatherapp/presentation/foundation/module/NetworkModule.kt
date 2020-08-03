package com.jobin.weatherapp.presentation.foundation.module

import androidx.annotation.NonNull
import com.jobin.weatherapp.data.api.WeatherDBAPI
import dagger.Module
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Retrofit API
@Module
class NetworkModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun weatherForcastDBAPI(retrofit: Retrofit): WeatherDBAPI {
        return retrofit.create(WeatherDBAPI::class.java)
    }
}