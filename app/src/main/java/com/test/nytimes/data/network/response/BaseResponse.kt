package com.test.nytimes.data.network.response

import com.google.gson.annotations.SerializedName

open class BaseResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)