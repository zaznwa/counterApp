package com.geeks.mvp.data.model.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExampleDto(
    @SerialName("id")
    val id: Int,
    @SerialName("value")
    val value: String,
)
