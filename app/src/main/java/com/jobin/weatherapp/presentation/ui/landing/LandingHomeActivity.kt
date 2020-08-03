package com.jobin.weatherapp.presentation.ui.landing

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jobin.weatherapp.R
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jobin.weatherapp.presentation.foundation.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LandingHomeActivity : BaseActivity<LandingHomeViewModel>(R.layout.activity_main) {

    @Inject
    lateinit var landingHomeViewModelFactory: LandingHomeViewModelFactory

    private var weatherForcastAdapter = WeatherForcastAdapter()

    override fun createViewModel(): LandingHomeViewModel {
        return ViewModelProviders.of(this, landingHomeViewModelFactory)
            .get(LandingHomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        daggerInjector.createLandingHomeComponent().inject(this)
        super.onCreate(savedInstanceState)
        setView()
        observeViewModel()
        setupViewListeners()
    }

    private fun setView() {
        rv_weather.adapter = weatherForcastAdapter
        rv_weather.setHasFixedSize(true)
        rv_weather.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun setupViewListeners() {
        weatherForcastAdapter.setWeatherClickListener { forcast ->
            //TODO : Click event
        }
    }

    private fun observeViewModel() {
        viewModel.getWeatherForcastLiveData().observe { forcastData ->
            weatherForcastAdapter.setItems(forcastData)
        }


        viewModel.getHideLoadingLiveData().observe {
            progressBar.visibility = View.GONE
        }

        viewModel.getShowLoadingLiveData().observe {
            progressBar.visibility = View.VISIBLE
        }

        viewModel.getShowErrorLiveData().observe { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
        viewModel.loadWeatherForcast()
    }
}

