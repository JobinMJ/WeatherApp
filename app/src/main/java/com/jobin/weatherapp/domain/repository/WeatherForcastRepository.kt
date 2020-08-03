package com.jobin.weatherapp.domain.repository

import com.jobin.weatherapp.domain.util.QueryResponse
import com.jobin.weatherapp.domain.model.WeatherForcast

interface WeatherForcastRepository {
     suspend fun getWeatherForcast(): QueryResponse<List<WeatherForcast>>
}