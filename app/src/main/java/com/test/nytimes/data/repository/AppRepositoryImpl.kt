package com.test.nytimes.data.repository

import com.test.nytimes.data.network.response.MostViewedResponse
import com.test.nytimes.data.network.service.ApiService
import com.test.nytimes.data.network.service.SafeApiCall

class AppRepositoryImpl(
    private val apiService: ApiService
) : AppRepository {
    override suspend fun getMostViewed(
        period: Int,
        api_key: String
    ): SafeApiCall<MostViewedResponse> {
        return try {
            val result = apiService.getMostViewed(period,api_key).await()
            SafeApiCall.Success(result)
        } catch (ex: Throwable) {
            SafeApiCall.Error(ex)
        }
    }


}