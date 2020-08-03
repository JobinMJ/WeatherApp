package com.jobin.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("dt") val dt: Int,
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("dt_txt") val dtTxt: String

) {
    data class Main(
        @SerializedName("temp_min") val temp_min: Double,
        @SerializedName("temp_max") val temp_max: Double
    )
}
