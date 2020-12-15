package com.test.nytimes.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.test.nytimes.data.network.response.BaseResponse
import com.test.nytimes.utils.NoConnectivityException
import com.test.nytimes.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.HttpException
import java.net.SocketTimeoutException
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String>()

    val objectiveUpdatedLiveData = MutableLiveData<Boolean>()

    // Coroutine's background job
    private val job = Job()

    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        // Clear our job when the linked activity is destroyed to avoid memory leaks
        job.cancel()
    }

    fun getErrorMessage(exception: Throwable): String? {
        var message = exception.message
        when (exception) {
            is HttpException -> {
                val errorJsonString = exception.response()?.errorBody()?.string()
                if (exception.code() != 500) {
                    message = try {
                        val errorResponse =
                            Gson().fromJson(errorJsonString, BaseResponse::class.java)
                        errorResponse.message
                    } catch (ex: Exception) {
                        ex.message
                    }

                }
                return message
            }
            is NoConnectivityException -> return "Check Your Internet.!"
            is SocketTimeoutException -> return "Request Timeout.!"
            is Exception -> return "Please Try Again Later.!"
            else -> return exception.message
        }
    }
}

