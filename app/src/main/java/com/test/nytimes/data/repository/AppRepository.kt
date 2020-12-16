package com.test.nytimes.data.repository

import androidx.lifecycle.LiveData
import com.test.nytimes.data.network.response.MostViewedResponse
import com.test.nytimes.data.network.service.SafeApiCall


interface AppRepository {

     fun getMostViewed(period: Int, api_key: String): LiveData<SafeApiCall<MostViewedResponse>>

}