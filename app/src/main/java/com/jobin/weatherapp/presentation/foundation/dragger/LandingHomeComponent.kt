package com.jobin.weatherapp.presentation.foundation.dragger

import com.jobin.weatherapp.presentation.ui.landing.LandingHomeActivity
import dagger.Subcomponent

@Subcomponent(modules = [LandingHomeModule::class])
interface LandingHomeComponent {
    fun inject(landingHomeActivity: LandingHomeActivity)
}