package com.jobin.weatherapp.presentation.ui.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jobin.weatherapp.domain.usecase.GetWeatherForcastUseCase
import com.jobin.weatherapp.presentation.dispatcher.DispatchersProvider


class LandingHomeViewModelFactory(
    private val getWeatherForcastUseCase: GetWeatherForcastUseCase,
    private val dispatchers: DispatchersProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LandingHomeViewModel(getWeatherForcastUseCase, dispatchers) as T
    }
}