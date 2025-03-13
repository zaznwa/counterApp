package com.geeks.mvp.domain.usecases

import com.geeks.mvp.domain.repository.CounterRepository
import javax.inject.Inject

class DecrementUseCase @Inject constructor(
    private val counterRepository: CounterRepository,
) {

    fun decrement() {
        counterRepository.decrement()
    }

}