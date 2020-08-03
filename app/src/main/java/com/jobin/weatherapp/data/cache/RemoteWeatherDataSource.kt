package com.jobin.weatherapp.data.cache

import com.jobin.weatherapp.domain.util.QueryResponse
import com.jobin.weatherapp.data.api.WeatherDBAPI
import com.jobin.weatherapp.data.api.WeatherDBWrapper
import com.jobin.weatherapp.domain.model.WeatherForcast
import java.lang.Exception

class RemoteWeatherDataSource(private val weatherDBAPI: WeatherDBAPI) : WeatherDataSource.Remote {

    override suspend fun getWeatherForcastFromServer(): QueryResponse<List<WeatherForcast>> {
        return try {
            val result = weatherDBAPI.getWeatherForcast("Singapore").await()
            QueryResponse.Success(result.list.map {
                WeatherDBWrapper.toWeatherForcast(it)
            })
        } catch (e: Exception) {
            QueryResponse.Error(e)
        }
    }

}