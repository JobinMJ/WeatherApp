package com.jobin.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jobin.weatherapp.domain.model.WeatherForcast

//DB
@Database(entities = [WeatherForcast::class], version = 1, exportSchema = false)
abstract class WeatherForcastDatabase : RoomDatabase() {
    abstract fun getWeatherForcastPojo(): WeatherForcastPojo
}