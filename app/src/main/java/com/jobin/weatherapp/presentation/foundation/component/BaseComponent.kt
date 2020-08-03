package com.jobin.weatherapp.presentation.foundation.component

import com.jobin.weatherapp.presentation.foundation.module.BaseModule
import com.jobin.weatherapp.presentation.foundation.module.DataSourceModule
import com.jobin.weatherapp.presentation.foundation.module.DatabaseModule
import com.jobin.weatherapp.presentation.foundation.module.NetworkModule
import com.jobin.weatherapp.presentation.foundation.dragger.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    BaseModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    DataSourceModule::class
])

interface BaseComponent {
    fun plus(landingHomeModule: LandingHomeModule): LandingHomeComponent
}