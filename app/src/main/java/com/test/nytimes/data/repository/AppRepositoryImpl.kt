package com.test.nytimes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.nytimes.data.network.response.MostViewedResponse
import com.test.nytimes.data.network.service.ApiService
import com.test.nytimes.data.network.service.SafeApiCall
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppRepositoryImpl(
    private val apiService: ApiService
) : AppRepository {
    override fun getMostViewed(
        period: Int,
        api_key: String
    ): LiveData<SafeApiCall<MostViewedResponse>> {
        val resultLiveData = MutableLiveData<SafeApiCall<MostViewedResponse>>()
        GlobalScope.launch {
            resultLiveData.postValue(
                try {
                    val result = apiService.getMostViewed(period, api_key).await()
                    SafeApiCall.Success(result)
                } catch (ex: Throwable) {
                    SafeApiCall.Error(ex)
                }
            )
        }

        return resultLiveData
    }
}