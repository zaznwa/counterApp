package com.geeks.mvp.domain.usecases

import com.geeks.mvp.domain.repository.CounterRepository

class GetCountUseCase (
    private val counterRepository: CounterRepository
){

    operator fun  invoke() = counterRepository.getCount()

}