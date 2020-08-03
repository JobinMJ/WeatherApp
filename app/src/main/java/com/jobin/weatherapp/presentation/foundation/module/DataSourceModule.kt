package com.jobin.weatherapp.presentation.foundation.module

import com.jobin.weatherapp.data.api.WeatherDBAPI
import com.jobin.weatherapp.data.cache.*
import com.jobin.weatherapp.data.database.WeatherForcastPojo
import com.jobin.weatherapp.domain.repository.WeatherForcastRepository
import com.jobin.weatherapp.domain.usecase.GetWeatherForcastUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//Source of Data
@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideWeatherForcastRepository(weatherRemote: WeatherDataSource.Remote,
                                        weatherLocal: WeatherDataSource.Local,
                                        weatherCache: WeatherDataSource.Cache): WeatherForcastRepository {
        return RepositoryRouter(
            weatherRemote,
            weatherLocal,
            weatherCache
        )
    }

    @Provides
    @Singleton
    fun provideWeatherForcastLocalDataSource(
        executor: DiskExecutor, weatherForcastPojo: WeatherForcastPojo
    ): WeatherDataSource.Local {
        return LocalWeatherDataSource(
            executor,
            weatherForcastPojo
        )
    }

    @Provides
    @Singleton
    fun provideWeatherForcastCacheDataSource(): WeatherDataSource.Cache {
        return CacheDataSource()
    }


    @Provides
    @Singleton
    fun provideWeatherForcastRemoveDataSource(weatherDBAPI: WeatherDBAPI): WeatherDataSource.Remote {
        return RemoteWeatherDataSource(weatherDBAPI)
    }

    @Provides
    fun provideWeatherForcastUseCase(weatherForcastRepository: WeatherForcastRepository): GetWeatherForcastUseCase {
        return GetWeatherForcastUseCase(weatherForcastRepository)
    }



}