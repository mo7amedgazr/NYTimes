package com.test.nytimes.data.network.service

import com.test.nytimes.data.network.response.MostViewedResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface ApiService {

    @GET("viewed/{period}.json")
    fun getMostViewed(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): Deferred<MostViewedResponse>

}