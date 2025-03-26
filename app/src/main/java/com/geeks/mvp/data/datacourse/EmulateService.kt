package com.geeks.mvp.data.datacourse

import com.geeks.mvp.data.model.CounterDto

class EmulateService () {

    private var count = 0
    private var isIncrement = false

    fun increment() {
        count++
        isIncrement = true
    }

    fun decrement() {
        count--
        isIncrement = false
    }

    fun getCount(): CounterDto {
        return CounterDto(
            operationType = if (isIncrement) "increment" else "decrement",
            count = count
        )
    }


}