package com.test.nytimes.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.nytimes.data.network.service.SafeApiCall
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

import com.test.nytimes.data.repository.AppRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import retrofit2.Response

class MainViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var testDispatcher: TestCoroutineDispatcher
    private lateinit var testScope: TestCoroutineScope

    @Before
    fun setUp() {
        testDispatcher = TestCoroutineDispatcher()
        testScope = TestCoroutineScope(testDispatcher)

        Dispatchers.setMain(testDispatcher)
    }
    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun verify_loading_shown_when_getMostViewed_articles() {
        val viewModel = MainViewModel(mockkClass(AppRepository::class, relaxed = true))
        viewModel.showLoading.observeForever { }
        viewModel.getMostViewed()
        assert(viewModel.showLoading.value == true)
    }

    @Test
    fun verify_loading_not_shown_when_getMostViewed_articles() {
        val viewModel = MainViewModel(mockkClass(AppRepository::class, relaxed = true))
        viewModel.showLoading.observeForever { }
        assert(viewModel.showLoading.value == null)
    }


    @Test
    fun verify_() = testScope.runBlockingTest {
        val appRepo = mockkClass(AppRepository::class)


        every { runBlockingTest { appRepo.getMostViewed(any(), any()) } }.answers {
            SafeApiCall.Error(
                Throwable("custom error message")
            )
        }

        val viewModel = MainViewModel(appRepo)
        viewModel.showLoading.observeForever { }
        viewModel.getMostViewed()
        assert(viewModel.showLoading.value == false)
    }

    @Test
    fun test_getMostViewed() = testScope.runBlockingTest {

    }
}