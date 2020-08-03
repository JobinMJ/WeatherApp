package com.jobin.weatherapp.data.api

import com.jobin.weatherapp.data.model.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WeatherDBAPI {
    @GET("forecast?")
    fun getWeatherForcast(
        @Query("q") q: String
    ): Deferred<WeatherForcastResponse>
}