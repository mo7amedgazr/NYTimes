package com.test.nytimes.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.nytimes.utils.NoConnectivityException
import com.test.nytimes.utils.SingleLiveEvent
import retrofit2.HttpException
import java.net.SocketTimeoutException

open class BaseViewModel : ViewModel() {
    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String>()


    fun getErrorMessage(exception: Throwable): String? {
        val message = exception.message
        return when (exception) {
            is HttpException -> {
                when (exception.code()) {
                    401 -> "Unauthorized.!"
                    else -> message
                }
            }
            is NoConnectivityException -> "Check Your Internet.!"
            is SocketTimeoutException -> "Request Timeout.!"
            is Exception -> "Please Try Again Later.!"
            else -> exception.message
        }
    }
}

