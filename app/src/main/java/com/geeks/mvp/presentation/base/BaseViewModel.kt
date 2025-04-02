package com.geeks.mvp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.mvp.domain.utils.Either
import com.geeks.mvp.presentation.ui.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel() : ViewModel() {
    protected fun <T> collectRequest(
        state: MutableStateFlow<UiState<T>>,
        dispatcher: CoroutineDispatcher,
        request: suspend () -> Flow<Either<Throwable, T>>,
    ) {
        viewModelScope.launch(dispatcher) {
            state.value = UiState.Loading
            request().collect { result ->
                state.value = when (result) {
                    is Either.Left -> UiState.Error(result.error.localizedMessage.orEmpty())
                    is Either.Right -> UiState.Success(result.data)
                }
            }
        }
    }
}

