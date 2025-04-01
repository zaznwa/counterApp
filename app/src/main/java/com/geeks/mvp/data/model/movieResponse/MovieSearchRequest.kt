package com.geeks.mvp.data.model.movieResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchRequest(
    @SerialName("query")
    val query: String,

    @SerialName("page")
    val page: Int = 1,

    @SerialName("limit")
    val limit: Int = 10
)