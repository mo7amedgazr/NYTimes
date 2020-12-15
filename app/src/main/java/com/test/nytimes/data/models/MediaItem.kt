package com.test.nytimes.data.models

import com.google.gson.annotations.SerializedName

data class MediaItem(

    @field:SerializedName("copyright")
	val copyright: String? = null,

    @field:SerializedName("media-metadata")
	val mediaMetadata: List<MediaMetadataItem?>? = null,

    @field:SerializedName("subtype")
	val subtype: String? = null,

    @field:SerializedName("caption")
	val caption: String? = null,

    @field:SerializedName("type")
	val type: String? = null,

    @field:SerializedName("approved_for_syndication")
	val approvedForSyndication: Int? = null
)