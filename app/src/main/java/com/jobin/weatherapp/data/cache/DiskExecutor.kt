package com.jobin.weatherapp.data.cache

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DiskExecutor : Executor {
    private val diskExecutor: Executor = Executors.newSingleThreadExecutor()
    override fun execute(runnable: Runnable) {
        diskExecutor.execute(runnable)
    }
}