package com.jobin.weatherapp.presentation.ui.util

import android.content.Context
import android.text.format.DateUtils
import com.jobin.weatherapp.R
import java.util.*


object Utility {

    fun convertKelvinToCelsius(context: Context, kelvin: Double): String {
        val temp = kelvin - 273.15
        val temperatureFormatResourceId = R.string.format_temperature
        return String.format(context.getString(temperatureFormatResourceId), temp)
    }

    fun getWeatherConditionImage(weatherId: Int): Int {
        return when (weatherId) {
            in 300..321 -> R.drawable.art_light_rain
            in 500..504 -> R.drawable.art_rain
            in 520..531 -> R.drawable.art_rain
            800 -> R.drawable.art_clear
            801 -> R.drawable.art_light_clouds
            in 802..804 -> R.drawable.art_clouds
            in 951..957 -> R.drawable.art_clear
            else -> R.drawable.art_clear
        }
    }


}
