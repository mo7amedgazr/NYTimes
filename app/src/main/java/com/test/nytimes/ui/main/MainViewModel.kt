package com.test.nytimes.ui.main

import androidx.lifecycle.MediatorLiveData
import com.test.nytimes.data.network.response.MostViewedResponse
import com.test.nytimes.data.network.service.SafeApiCall
import com.test.nytimes.data.repository.AppRepository
import com.test.nytimes.ui.base.BaseViewModel
import com.test.nytimes.utils.api_key
import com.test.nytimes.utils.articlesPeriod

class MainViewModel(
    private val appRepository: AppRepository
) : BaseViewModel() {

    val mostViewedLiveData: MediatorLiveData<MostViewedResponse> =
        MediatorLiveData<MostViewedResponse>()

    fun getMostViewed() {
        showLoading.value = true
        val resultLiveData = appRepository.getMostViewed(
            period = articlesPeriod, api_key = api_key
        )
        mostViewedLiveData.addSource(resultLiveData) {
            showLoading.value = false
            if (it is SafeApiCall.Success<*>) {
                mostViewedLiveData.value = it.data as? MostViewedResponse
            } else {
                showError.value = getErrorMessage((resultLiveData.value as SafeApiCall.Error).exception)
            }
        }
    }
}