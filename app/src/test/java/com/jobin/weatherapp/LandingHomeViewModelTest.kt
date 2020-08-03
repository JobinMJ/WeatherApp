package com.jobin.weatherapp

import androidx.lifecycle.Observer
import com.jobin.weatherapp.domain.model.WeatherForcast
import com.jobin.weatherapp.domain.usecase.GetWeatherForcastUseCase
import com.jobin.weatherapp.domain.util.QueryResponse
import com.jobin.weatherapp.presentation.ui.landing.LandingHomeViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LandingHomeViewModelTest : BaseViewModelTest() {

    @Mock
    lateinit var getWeatherForcastUseCase: GetWeatherForcastUseCase

    private lateinit var viewModel: LandingHomeViewModel

    @Before
    fun setUp() {
        viewModel = LandingHomeViewModel(getWeatherForcastUseCase, testRule.dispatchersProvider)
    }

    @Test
    fun hide_progrssbar_and_show_all_received_forcast_when_api_sucess() {
        testRule.runBlockingTest {
            val forcast: Observer<List<WeatherForcast>> = mock()
            val showLoading: Observer<Unit> = mock()
            val hideLoading: Observer<Unit> = mock()
            viewModel.getWeatherForcastLiveData().observeForever(forcast)
            viewModel.getShowLoadingLiveData().observeForever(showLoading)
            viewModel.getHideLoadingLiveData().observeForever(hideLoading)
            Mockito.`when`(getWeatherForcastUseCase.execute())
                .thenReturn(QueryResponse.Success(mock()))
            viewModel.loadWeatherForcast()
            val inOrder = Mockito.inOrder(showLoading, hideLoading, forcast)
            inOrder.verify(showLoading).onChanged(Unit)
            inOrder.verify(hideLoading).onChanged(Unit)
            inOrder.verify(forcast).onChanged(any())
        }
    }


    @Test
    fun hide_progrssbar_and_show_error_popup_when_api_failed() {
        testRule.runBlockingTest {
            val forcast: Observer<List<WeatherForcast>> = mock()
            val error: Observer<String> = mock()
            val showLoading: Observer<Unit> = mock()
            val hideLoading: Observer<Unit> = mock()
            viewModel.getWeatherForcastLiveData().observeForever(forcast)
            viewModel.getShowErrorLiveData().observeForever(error)
            viewModel.getShowLoadingLiveData().observeForever(showLoading)
            viewModel.getHideLoadingLiveData().observeForever(hideLoading)
            Mockito.`when`(getWeatherForcastUseCase.execute()).thenReturn(QueryResponse.Error(mock()))
            viewModel.loadWeatherForcast()
            val inOrder = Mockito.inOrder(showLoading, hideLoading, error)
            inOrder.verify(showLoading).onChanged(Unit)
            inOrder.verify(hideLoading).onChanged(Unit)
            inOrder.verify(error).onChanged(any())
            Mockito.verifyZeroInteractions(forcast)
        }
    }

    @Test
    fun show_progress_bar_while_loading() {
        testRule.runBlockingTest {
            val showLoading: Observer<Unit> = mock()
            viewModel.getShowLoadingLiveData().observeForever(showLoading)
            viewModel.loadWeatherForcast()
            Mockito.verify(showLoading).onChanged(Unit)
        }
    }
}