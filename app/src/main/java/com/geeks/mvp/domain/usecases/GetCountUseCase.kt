package com.geeks.mvp.domain.usecases

import com.geeks.mvp.domain.repository.CounterRepository
import javax.inject.Inject

class GetCountUseCase @Inject constructor (
    private val counterRepository: CounterRepository
){

    fun  getCount() = counterRepository.getCount()

}