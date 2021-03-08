package com.briggin.coroutineplayground.api.model

import com.google.gson.annotations.SerializedName

data class RequestBody(
    @SerializedName("searchString")
    val query: String,
    @SerializedName("searchType")
    val type: String,
    @SerializedName("offset")
    val offset: Long
)
