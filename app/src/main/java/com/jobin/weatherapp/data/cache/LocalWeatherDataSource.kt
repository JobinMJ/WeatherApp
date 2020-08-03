package com.jobin.weatherapp.data.cache

import com.jobin.weatherapp.domain.util.QueryResponse
import com.jobin.weatherapp.data.database.WeatherForcastPojo
import com.jobin.weatherapp.domain.model.WeatherForcast

class LocalWeatherDataSource(
    private val executor: DiskExecutor,
    private val weatherForcastPojo: WeatherForcastPojo)
    : WeatherDataSource.Local {

    override suspend fun getWeatherForcastFromServer(): QueryResponse<List<WeatherForcast>> {
        val weatherForcasts = weatherForcastPojo.getWeatherForcast()
        return if (weatherForcasts.isNotEmpty()) {
            QueryResponse.Success(weatherForcasts)
        } else {
            QueryResponse.Error(Throwable("Data Not Available"))
        }
    }

    override fun saveWeatherForcastToDB(weatherForcasts: List<WeatherForcast>) {
        executor.execute {
            weatherForcastPojo.insertWeatherForcast(weatherForcasts)
        }
    }


}