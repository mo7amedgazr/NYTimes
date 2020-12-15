package com.test.nytimes.data.network.response

import com.google.gson.annotations.SerializedName
import com.test.nytimes.data.models.ResultsItem

data class MostViewedResponse(

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("num_results")
	val numResults: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)