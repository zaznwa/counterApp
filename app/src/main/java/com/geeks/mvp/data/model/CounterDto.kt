package com.geeks.mvp.data.model

import com.geeks.mvp.domain.model.OperationType

data class CounterDto (
    val operationType: String,
    val count: Int
)