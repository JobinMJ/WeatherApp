package com.jobin.weatherapp.presentation.foundation.dragger

import dagger.Module
import dagger.Provides
import com.jobin.weatherapp.domain.usecase.GetWeatherForcastUseCase
import com.jobin.weatherapp.presentation.ui.landing.LandingHomeViewModelFactory
import com.jobin.weatherapp.presentation.dispatcher.DispatchersProvider

@Module
class LandingHomeModule {
    @Provides
    fun provideLandingHomeViewModuleFactory(getWeatherForcastUseCase: GetWeatherForcastUseCase, dispatchersProvider: DispatchersProvider): LandingHomeViewModelFactory {
        return LandingHomeViewModelFactory(getWeatherForcastUseCase,  dispatchersProvider)
    }
}
