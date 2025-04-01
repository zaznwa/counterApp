package com.geeks.mvp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.mvp.domain.utils.Either
import com.geeks.mvp.presentation.ui.UiState
import com.geeks.mvp.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(

) {

    protected fun <T> MutableStateFlow<UiState<T>>.collectFlow(
        case: Flow<Either<Throwable, T>>,
        dispatcher: CoroutineDispatcher,
    ) {
        viewModelScope.launch(dispatcher) {
            this@collectFlow.value = UiState.Loading

            case.collect { result ->
                this@collectFlow.value = when (result) {
                    is Either.Left -> UiState.Error(result.error.localizedMessage.orEmpty())
                    is Either.Right -> UiState.Success(result.data)
                }
            }
        }
    }
}

