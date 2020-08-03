package com.jobin.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito

open class BaseViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testRule = TestRule()

    /**
     * Returns Mockito.any() as nullable type to avoid java.lang.IllegalStateException when
     * null is returned.
     */
    fun <T> any(): T = Mockito.any<T>()

    /**
     * Gives you the ability to mock observes programmatically
     * **/
    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}