package com.jobin.weatherapp.presentation.ui.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jobin.weatherapp.domain.model.WeatherForcast
import com.jobin.weatherapp.domain.usecase.GetWeatherForcastUseCase
import com.jobin.weatherapp.domain.util.QueryResponse
import com.jobin.weatherapp.presentation.dispatcher.DispatchersProvider
import com.jobin.weatherapp.presentation.foundation.ui.BaseViewModel


class LandingHomeViewModel internal constructor(
    private val getWeatherForcastUseCase: GetWeatherForcastUseCase,
    dispatchers: DispatchersProvider)
    : BaseViewModel(dispatchers) {

    private val weatherForcastLiveData: MutableLiveData<List<WeatherForcast>> = MutableLiveData()
    private val showLoadingLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val hideLoadingLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val showErrorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getWeatherForcastLiveData(): LiveData<List<WeatherForcast>> = weatherForcastLiveData
    fun getShowLoadingLiveData(): LiveData<Unit> = showLoadingLiveData
    fun getHideLoadingLiveData(): LiveData<Unit> = hideLoadingLiveData
    fun getShowErrorLiveData(): LiveData<String> = showErrorLiveData

    fun loadWeatherForcast() {
        getWeatherForcast()
    }

    private fun getWeatherForcast() {
        showLoadingLiveData.postValue(Unit)
        execute {
            when (val result = getWeatherForcastUseCase.execute()) {
                is QueryResponse.Success -> {
                    hideLoadingLiveData.postValue(Unit)
                    weatherForcastLiveData.postValue(result.data)
                }
                is QueryResponse.Error -> {
                    hideLoadingLiveData.postValue(Unit)
                    showErrorLiveData.postValue(result.error.message)
                }
            }
        }
    }



}