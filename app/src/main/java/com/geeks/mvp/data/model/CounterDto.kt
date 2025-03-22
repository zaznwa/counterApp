package com.geeks.mvp.data.model

import com.geeks.mvp.domain.model.OperationType
import com.google.gson.annotations.SerializedName

data class CounterDto (
    @SerializedName("operationType")
    val operationType: String,
    val count: Int
)