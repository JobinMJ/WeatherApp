package com.jobin.weatherapp.presentation.foundation.module

import android.content.Context
import androidx.room.Room
import com.jobin.weatherapp.data.database.WeatherForcastPojo
import com.jobin.weatherapp.data.database.WeatherForcastDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideWeatherForcastDatabase(context: Context): WeatherForcastDatabase {
        return Room.databaseBuilder(context, WeatherForcastDatabase::class.java, "WeatherForcast.db").build()
    }

    @Provides
    fun weatherForcastPojo(weatherForcastDatabase: WeatherForcastDatabase): WeatherForcastPojo {
        return weatherForcastDatabase.getWeatherForcastPojo()
    }
}