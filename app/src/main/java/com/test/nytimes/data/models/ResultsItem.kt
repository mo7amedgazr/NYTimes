package com.test.nytimes.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultsItem(

    @field:SerializedName("per_facet")
    val perFacet: List<Any?>? = null,

    @field:SerializedName("eta_id")
    val etaId: Int? = null,

    @field:SerializedName("subsection")
    val subsection: String? = null,

    @field:SerializedName("org_facet")
    val orgFacet: List<Any?>? = null,

    @field:SerializedName("nytdsection")
    val nytdsection: String? = null,

    @field:SerializedName("column")
    val column: Any? = null,

    @field:SerializedName("section")
    val section: String? = null,

    @field:SerializedName("asset_id")
    val assetId: Long? = null,

    @field:SerializedName("source")
    val source: String? = null,

    @field:SerializedName("abstract")
    val abstract: String? = null,

    @field:SerializedName("media")
    val media: List<MediaItem?>? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("des_facet")
    val desFacet: List<String?>? = null,

    @field:SerializedName("uri")
    val uri: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("adx_keywords")
    val adxKeywords: String? = null,

    @field:SerializedName("geo_facet")
    val geoFacet: List<Any?>? = null,

    @field:SerializedName("id")
    val id: Long? = null,

    @field:SerializedName("published_date")
    val publishedDate: String? = null,

    @field:SerializedName("updated")
    val updated: String? = null,

    @field:SerializedName("byline")
    val byline: String? = null
) : Serializable