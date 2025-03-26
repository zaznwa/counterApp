package com.geeks.mvp.domain.usecases

import com.geeks.mvp.domain.repository.CounterRepository

class IncrementUseCase (
    private val counterRepository: CounterRepository,
) {

   operator fun invoke() = counterRepository.increment()


}