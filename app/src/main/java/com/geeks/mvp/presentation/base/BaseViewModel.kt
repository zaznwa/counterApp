package com.geeks.mvp.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.mvp.presentation.utils.Either
import com.geeks.mvp.presentation.utils.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
) : ViewModel() {
    protected fun <T> collectRequest(
        request: suspend () -> Flow<Either<Throwable, T>>,
        state: MutableStateFlow<UiState<T>>,
        dispatcher: CoroutineDispatcher,
    ) {
        viewModelScope.launch(dispatcher) {
            state.value = UiState.Loading
            Log.d("ololo", "Загрузка...")
            request().collect { result ->
                state.value = when (result) {

                    is Either.Error -> {
                        Log.e("ololo", "Ошибка: ${result.error.localizedMessage}") // Логируем ошибку
                        UiState.Error(result.error.localizedMessage.orEmpty())
                    }

                    is Either.Success -> {
                        Log.d("ololo", "Успех: ${result.data}")
                        val data = result.data
                        if (data is Collection<*> && data.isEmpty()) {
                            UiState.Empty
                        } else {
                            UiState.Success(data)
                        }
                    }
                }
            }
        }
    }
}


