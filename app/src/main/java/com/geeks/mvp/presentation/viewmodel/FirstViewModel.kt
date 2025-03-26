package com.geeks.mvp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geeks.mvp.domain.model.CounterEntity
import com.geeks.mvp.domain.usecases.DecrementUseCase
import com.geeks.mvp.domain.usecases.GetCountUseCase
import com.geeks.mvp.domain.usecases.IncrementUseCase


class FirstViewModel (
    private val incrementUseCase: IncrementUseCase,
    private val decrementUseCase: DecrementUseCase,
    private val getCountUseCase: GetCountUseCase,
) : ViewModel() {

    private val _counter = MutableLiveData(getCountUseCase())
    val counter: LiveData<CounterEntity> = _counter

    fun increment() {
        incrementUseCase()
        getCounter()
    }

    fun decrement() {
        decrementUseCase()
        getCounter()
    }

    private fun getCounter() {
        _counter.value = getCountUseCase()
    }

}