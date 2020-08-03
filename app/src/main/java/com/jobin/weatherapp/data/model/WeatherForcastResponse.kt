package com.jobin.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherForcastResponse(
    @SerializedName("list") val list: List<WeatherData>
)