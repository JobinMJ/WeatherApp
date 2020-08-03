package com.jobin.weatherapp.presentation.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    fun getIO(): CoroutineDispatcher
    fun getMain(): CoroutineDispatcher
    fun getDefault(): CoroutineDispatcher
}