package com.jobin.weatherapp.data.cache

import com.jobin.weatherapp.domain.util.QueryResponse
import com.jobin.weatherapp.domain.model.WeatherForcast

interface WeatherDataSource {

    interface Remote {
        suspend fun getWeatherForcastFromServer(): QueryResponse<List<WeatherForcast>>
    }

    interface Dynamic {
        suspend fun getWeatherForcastFromServer(): QueryResponse<List<WeatherForcast>>
    }

    interface Local : Dynamic {
        fun saveWeatherForcastToDB(weatherForcasts: List<WeatherForcast>)
    }

    interface Cache : Local
}