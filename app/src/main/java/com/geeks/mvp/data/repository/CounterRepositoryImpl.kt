package com.geeks.mvp.data.repository

import com.geeks.mvp.data.datacourse.EmulateService
import com.geeks.mvp.data.mapper.toDomain
import com.geeks.mvp.domain.model.CounterEntity
import com.geeks.mvp.domain.repository.CounterRepository

class CounterRepositoryImpl (
    private val api: EmulateService
) : CounterRepository {
    override fun increment() {
        api.increment()
    }

    override fun decrement() {
        api.decrement()
    }

    override fun getCount(): CounterEntity {
        val response = api.getCount()
        return response.toDomain()
    }


}