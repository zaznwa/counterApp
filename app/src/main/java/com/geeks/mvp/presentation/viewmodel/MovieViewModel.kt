package com.geeks.mvp.presentation.viewmodel

import com.geeks.mvp.domain.model.Example
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.usecases.networkUseCases.GetExampleUseCase
import com.geeks.mvp.domain.usecases.networkUseCases.GetMovieUseCase
import com.geeks.mvp.presentation.base.BaseViewModel
import com.geeks.mvp.presentation.ui.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    private val ioDispatcher: CoroutineDispatcher,
    private val mainDispatcher: CoroutineDispatcher,
    private val getExampleUseCase: GetExampleUseCase,
) : BaseViewModel() {


    private val _movieState = MutableStateFlow<UiState<List<MovieEntity>>>(UiState.Initial)
    val movieState = _movieState.asStateFlow()


    private val _exampleState = MutableStateFlow<UiState<Example>>(UiState.Initial)
    val exampleState = _exampleState.asStateFlow()


//    private val _errorMessageState = MutableStateFlow<String?>(null)
//    val errorMessageState: MutableStateFlow<String?> = _errorMessageState

//    private val _movieState = MutableStateFlow("")
//    val movieState= _movieState.asStateFlow()

    fun searchMovies(query: String) {
        collectRequest(
            request = { getMovieUseCase.invoke(query) },
            state = _movieState,
            dispatcher = ioDispatcher
        )
    }

    fun exampleRequest() {
        collectRequest(state = _exampleState, dispatcher =  mainDispatcher) {
            getExampleUseCase.invoke()
        }
    }


//    sealed class UiState<out T> {
//        data object Initial : UiState<Nothing>()
//        data object Loading : UiState<Nothing>()
//        data class Success<T>(val data: T) : UiState<T>()
//        data class Error(val message: String) : UiState<Nothing>()
//        data object Empty : UiState<Nothing>()
//    }
}
