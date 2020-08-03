package com.jobin.weatherapp.domain.usecase

import com.jobin.weatherapp.domain.util.QueryResponse
import com.jobin.weatherapp.domain.model.WeatherForcast
import com.jobin.weatherapp.domain.repository.WeatherForcastRepository

open class GetWeatherForcastUseCase(private val weatherForcastRepository: WeatherForcastRepository) {

    suspend fun execute(): QueryResponse<List<WeatherForcast>> {
        return weatherForcastRepository.getWeatherForcast()
    }

}
