package com.geeks.mvp.domain.repository

import com.geeks.mvp.domain.model.CounterEntity

interface CounterRepository {

    fun increment()

    fun decrement()

    fun getCount(): CounterEntity


}