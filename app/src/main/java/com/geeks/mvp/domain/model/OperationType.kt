package com.geeks.mvp.domain.model

enum class OperationType(
    val value: String,

    ) {
    INCREMENT("increment"),
    DECREMENT("decrement"),
    UNKNOWN("unknown");

    companion object {
        fun fromString(value: String): OperationType =
            OperationType.entries.find { value == it.value } ?: UNKNOWN
    }
}