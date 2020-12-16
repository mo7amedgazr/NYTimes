package com.test.nytimes.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.nytimes.data.network.response.MostViewedResponse
import com.test.nytimes.data.network.service.SafeApiCall
import com.test.nytimes.data.repository.AppRepository
import com.test.nytimes.ui.base.BaseViewModel
import com.test.nytimes.utils.api_key
import com.test.nytimes.utils.articlesPeriod
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val appRepository: AppRepository
) : BaseViewModel() {

    val mostViewedLiveData = MutableLiveData<MostViewedResponse>()

    fun getMostViewed() {
        showLoading.value = true
        viewModelScope.launch {
            val result =
                withContext(Dispatchers.IO) {
                    appRepository.getMostViewed(
                        period = articlesPeriod, api_key = api_key
                    )
                }
            showLoading.value = false
            when (result) {
                is SafeApiCall.Success -> mostViewedLiveData.value = result.data
                is SafeApiCall.Error -> showError.value = getErrorMessage(result.exception)
            }
        }
    }


}