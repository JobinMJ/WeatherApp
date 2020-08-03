package com.jobin.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jobin.weatherapp.domain.model.WeatherForcast

//Room DB
@Dao
interface WeatherForcastPojo {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherForcast(weatherForcasts: List<WeatherForcast?>?)

    @Query("DELETE FROM WeatherForcast")
    fun deleteWeatherForcast()

    @Query("SELECT * FROM WeatherForcast")
    fun getWeatherForcast(): List<WeatherForcast>

}