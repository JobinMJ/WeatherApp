package com.jobin.weatherapp.presentation.foundation.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import com.jobin.weatherapp.presentation.dispatcher.DispatchersProvider
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
        private val dispatchers: DispatchersProvider
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatchers.getMain() + SupervisorJob()

    fun execute(job: suspend () -> Unit) = launch {
        withContext(dispatchers.getIO()) { job.invoke() }
    }

}