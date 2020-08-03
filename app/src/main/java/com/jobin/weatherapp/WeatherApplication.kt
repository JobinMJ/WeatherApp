package com.jobin.weatherapp

import android.app.Application
import com.jobin.weatherapp.presentation.foundation.dragger.DaggerInjector
import com.jobin.weatherapp.presentation.foundation.component.BaseComponent
import com.jobin.weatherapp.presentation.foundation.module.BaseModule
import com.jobin.weatherapp.presentation.foundation.module.DataSourceModule
import com.jobin.weatherapp.presentation.foundation.module.DatabaseModule
import com.jobin.weatherapp.presentation.foundation.module.NetworkModule
import com.jobin.weatherapp.presentation.foundation.dragger.*
import com.jobin.weatherapp.presentation.foundation.component.DaggerBaseComponent

class WeatherApplication : Application(),
    DaggerInjector {

    private lateinit var baseComponent: BaseComponent

    override fun onCreate() {
        super.onCreate()
        baseComponent = DaggerBaseComponent.builder()
            .baseModule(BaseModule(applicationContext))
            .databaseModule(DatabaseModule())
            .dataSourceModule(DataSourceModule())
            .networkModule(NetworkModule(BuildConfig.BASE_API_URL))
            .build()
    }

    override fun createLandingHomeComponent(): LandingHomeComponent {
        return baseComponent.plus(LandingHomeModule())
    }

}