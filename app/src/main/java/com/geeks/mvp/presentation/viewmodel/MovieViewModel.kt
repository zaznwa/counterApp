package com.geeks.mvp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.usecases.MovieUseCase
import com.geeks.mvp.domain.utils.Either
import com.geeks.mvp.presentation.ui.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val moviesUseCase: MovieUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {


    private val _movieState = MutableStateFlow<UiState<List<MovieEntity>>>(UiState.Initial)
    val movieState = _movieState.asStateFlow()

    private val _errorMessageState = MutableStateFlow<String?>(null)
    val errorMessageState = _errorMessageState.asStateFlow()

//    private val _movieState = MutableStateFlow("")
//    val movieState= _movieState.asStateFlow()

    fun searchMovies(query: String) {
        viewModelScope.launch {
            _movieState.value = UiState.Loading
            _errorMessageState.value = null

            moviesUseCase(query).collect { result ->
                when (result) {
                    is Either.Left -> {
                        UiState.Error(result.error.message ?: "Ошибка")
                        _errorMessageState.value = result.error.localizedMessage
                    }

                    is Either.Right -> {
                        _movieState.value = if (result.data.isEmpty()) {
                            UiState.Empty
                        } else {
                            UiState.Success(result.data)
                        }
                    }
                }
            }
        }
    }
}