package com.test.nytimes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.nytimes.data.network.response.MostViewedResponse
import com.test.nytimes.data.network.service.SafeApiCall

class FakeSuccessAppRepository : AppRepository {
    override  fun getMostViewed(
        period: Int,
        api_key: String
    ): LiveData<SafeApiCall<MostViewedResponse>> {
       return MutableLiveData(SafeApiCall.Success(MostViewedResponse()))
    }
}