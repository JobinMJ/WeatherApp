package com.jobin.weatherapp

import com.jobin.weatherapp.presentation.dispatcher.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class TestRule : TestWatcher() {

    internal val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    val dispatchersProvider = object : DispatchersProvider {
        override fun getIO(): CoroutineDispatcher = testCoroutineDispatcher
        override fun getMain(): CoroutineDispatcher = testCoroutineDispatcher
        override fun getDefault(): CoroutineDispatcher = testCoroutineDispatcher
    }

}

@OptIn(ExperimentalCoroutinesApi::class)
fun TestRule.runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
    testCoroutineDispatcher.runBlockingTest(block)
}