package com.jobin.weatherapp.presentation.foundation.module

import android.content.Context
import com.jobin.weatherapp.data.cache.DiskExecutor
import dagger.Module
import dagger.Provides
import com.jobin.weatherapp.presentation.dispatcher.DispatchersProvider
import com.jobin.weatherapp.presentation.dispatcher.DispatchersProviderImpl
import javax.inject.Singleton

@Module
class BaseModule constructor(context: Context) {

    private val appContext = context.applicationContext

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @Provides
    fun provideDiskExecutor(): DiskExecutor {
        return DiskExecutor()
    }

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider {
        return DispatchersProviderImpl()
    }

}