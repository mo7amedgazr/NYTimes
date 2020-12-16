package com.test.nytimes.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

import com.test.nytimes.data.repository.AppRepository
import com.test.nytimes.data.repository.FakeErrorAppRepository
import com.test.nytimes.data.repository.FakeSuccessAppRepository
import com.test.nytimes.utils.MainCoroutineScopeRule
import io.mockk.mockkClass
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.*
import org.junit.After

@ExperimentalCoroutinesApi
class MainViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()


    @Before
    fun setUp() {

    }


    @Test
    fun verify_loading_shown_when_getMostViewed_articles() {
        val viewModel = MainViewModel(mockkClass(AppRepository::class, relaxed = true))
        viewModel.showLoading.observeForever { }
        viewModel.mostViewedLiveData.observeForever {  }
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
    fun verify_error_show_when_get_most_viewed_returns_error()  {
        val appRepo = FakeErrorAppRepository()

        val viewModel = MainViewModel(appRepo)
        viewModel.showError.observeForever {}
        viewModel.mostViewedLiveData.observeForever {  }
        viewModel.showLoading.observeForever {  }
        viewModel.getMostViewed()
        assertEquals("Error Throwable", viewModel.showError.value)
    }

    @Test
    fun verify_success_when_get_most_viewed_successes()  {
        val appRepo = FakeSuccessAppRepository()

        val viewModel = MainViewModel(appRepo)
        viewModel.mostViewedLiveData.observeForever {}
        viewModel.getMostViewed()
        assert(viewModel.mostViewedLiveData.value != null)
    }

}