package com.test.nytimes.data.repository

import com.test.nytimes.data.network.response.MostViewedResponse
import com.test.nytimes.data.network.service.SafeApiCall


interface AppRepository {

    suspend fun getMostViewed(period: Int, api_key: String): SafeApiCall<MostViewedResponse>

}