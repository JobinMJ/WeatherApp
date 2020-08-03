package com.jobin.weatherapp.data.cache

import android.util.SparseArray
import com.jobin.weatherapp.domain.util.QueryResponse
import com.jobin.weatherapp.domain.model.WeatherForcast

class CacheDataSource : WeatherDataSource.Cache {
    private val cachedWeatherForcast = SparseArray<WeatherForcast>()

    override suspend fun getWeatherForcastFromServer(): QueryResponse<List<WeatherForcast>> {
        return if (cachedWeatherForcast.size() > 0) {
            val weatherForcasts = arrayListOf<WeatherForcast>()
            for (i in 0 until cachedWeatherForcast.size()) {
                val key = cachedWeatherForcast.keyAt(i)
                weatherForcasts.add(cachedWeatherForcast[key])
            }
            QueryResponse.Success(weatherForcasts)
        } else {
            QueryResponse.Error(Throwable("No data available available"))
        }
    }

    override fun saveWeatherForcastToDB(weatherForcasts: List<WeatherForcast>) {
        cachedWeatherForcast.clear()
        for (weatherForcasts in weatherForcasts) {
            cachedWeatherForcast.put(weatherForcasts.dt, weatherForcasts)
        }
    }
}