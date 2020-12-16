package com.test.nytimes.ui.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.nytimes.utils.NoConnectivityException
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockkClass
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException

class BaseViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getErrorMessage_HttpExceptionCode401() {
        val baseViewModel = BaseViewModel()
        val httpException = mockkClass(HttpException::class, relaxed = true)
        every { httpException.code() }.returns(401)
        every { httpException.message }.returns("Something Went Wrong.!")
        assertEquals(
            "Unauthorized.!", baseViewModel.getErrorMessage(httpException)
        )
    }

    @Test
    fun getErrorMessage_HttpExceptionDiff_Code() {
        val baseViewModel = BaseViewModel()
        val httpException = mockkClass(HttpException::class, relaxed = true)
        every { httpException.code() }.returns(400)
        every { httpException.message }.returns("Something Went Wrong.!")
        assertEquals(
            "Something Went Wrong.!", baseViewModel.getErrorMessage(httpException)
        )
    }

    @Test
    fun getErrorMessage_NoConnectivityException() {
        val baseViewModel = BaseViewModel()
        val noConnectivityException = mockkClass(NoConnectivityException::class, relaxed = true)
        every { noConnectivityException.message }.returns("Something Went Wrong.!")
        assertEquals(
            "Check Your Internet.!", baseViewModel.getErrorMessage(noConnectivityException)
        )
    }

    @Test
    fun getErrorMessage_SocketTimeoutException() {
        val baseViewModel = BaseViewModel()
        val socketTimeoutException = mockkClass(SocketTimeoutException::class, relaxed = true)
        every { socketTimeoutException.message }.returns("Something Went Wrong.!")
        assertEquals(
            "Request Timeout.!", baseViewModel.getErrorMessage(socketTimeoutException)
        )
    }

    @Test
    fun getErrorMessage_Exception() {
        val baseViewModel = BaseViewModel()
        val exception = mockkClass(Exception::class, relaxed = true)
        every { exception.message }.returns("Something Went Wrong.!")
        assertEquals(
            "Please Try Again Later.!", baseViewModel.getErrorMessage(exception)
        )
    }

    @Test
    fun getErrorMessage_Throwable() {
        val baseViewModel = BaseViewModel()
        assertEquals(
            "Something Went Wrong.!", baseViewModel.getErrorMessage(Throwable("Something Went Wrong.!"))
        )
    }
}