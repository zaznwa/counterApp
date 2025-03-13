package com.geeks.mvp.domain.usecases

import com.geeks.mvp.domain.repository.CounterRepository
import javax.inject.Inject

class IncrementUseCase @Inject constructor (
    private val counterRepository: CounterRepository
){

      fun increment() {
          counterRepository.increment()
      }

}