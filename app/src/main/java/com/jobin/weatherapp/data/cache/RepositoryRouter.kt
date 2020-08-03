package com.jobin.weatherapp.data.cache

import com.jobin.weatherapp.domain.util.QueryResponse
import com.jobin.weatherapp.domain.model.WeatherForcast
import com.jobin.weatherapp.domain.repository.WeatherForcastRepository

class RepositoryRouter constructor(
    private val weatherRemote: WeatherDataSource.Remote,
    private val weatherLocal: WeatherDataSource.Local,
    private val weatherCache: WeatherDataSource.Cache
) : WeatherForcastRepository {

    override suspend fun getWeatherForcast(): QueryResponse<List<WeatherForcast>> {
        return getWeatherForcastFromCacheDataSource()
    }


    private suspend fun getWeatherForcastFromCacheDataSource(): QueryResponse<List<WeatherForcast>> {
        return when (val result = weatherCache.getWeatherForcastFromServer()) {
            is QueryResponse.Success -> {
                result
            }

            is QueryResponse.Error -> {
                getWeatherForcastFromLocalDataSource()
            }
        }
    }

    private suspend fun getWeatherForcastFromLocalDataSource(): QueryResponse<List<WeatherForcast>> {
        return when (val result = weatherLocal.getWeatherForcastFromServer()) {
            is QueryResponse.Success -> {
                refreshCache(result.data)
                result
            }

            is QueryResponse.Error -> {
                getWeatherForcastFromRemoteDataSource()
            }
        }
    }

    private suspend fun getWeatherForcastFromRemoteDataSource(): QueryResponse<List<WeatherForcast>> {

        val result = weatherRemote.getWeatherForcastFromServer()

        if (result is QueryResponse.Success) {
            saveWeatherForcast(result.data)
            refreshCache(result.data)
        }

        return result
    }

    private fun saveWeatherForcast(weatherForcasts: List<WeatherForcast>) {
        weatherLocal.saveWeatherForcastToDB(weatherForcasts)
    }

    private fun refreshCache(weatherForcasts: List<WeatherForcast>) {
        weatherCache.saveWeatherForcastToDB(weatherForcasts)
    }

}