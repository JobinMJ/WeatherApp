package com.jobin.weatherapp.data.api

import com.jobin.weatherapp.data.model.WeatherData
import com.jobin.weatherapp.domain.model.WeatherForcast

object WeatherDBWrapper {

    fun toWeatherForcast(weatherData: WeatherData): WeatherForcast {
        return WeatherForcast(
            dt = weatherData.dt,
            dtTxt = weatherData.dtTxt,
            tempMax = weatherData.main.temp_max,
            tempMin = weatherData.main.temp_min,
            id = weatherData.weather.get(0).id,
            main = weatherData.weather.get(0).main
        )
    }


}