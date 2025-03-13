package com.geeks.mvp.data.repository

import com.geeks.mvp.data.datacourse.EmulateService
import com.geeks.mvp.domain.model.CounterEntity
import com.geeks.mvp.domain.model.OperationType
import com.geeks.mvp.domain.repository.CounterRepository
import javax.inject.Inject

class CounterRepositoryImpl @Inject constructor(
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
        return CounterEntity(
            operationType = OperationType.fromString(response.operationType),
            count = response.count
        )
    }
}