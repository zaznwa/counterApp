package com.geeks.mvp.presentation.viewmodel

import android.util.Log
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.usecases.GetMovieUseCase
import com.geeks.mvp.presentation.base.BaseViewModel
import com.geeks.mvp.presentation.utils.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieViewModel(
    private val moviesUseCase: GetMovieUseCase,
    private val io: CoroutineDispatcher,
) : BaseViewModel() {


    private val _movieState = MutableStateFlow<UiState<List<MovieEntity>>>(UiState.Initial)
    val movieState = _movieState.asStateFlow()


    private val _errorMessageState = MutableStateFlow<String?>(null)
    val errorMessageState = _errorMessageState.asStateFlow()

//    private val _movieState = MutableStateFlow("")
//    val movieState= _movieState.asStateFlow()

    fun searchMovies(query: String) {
        collectRequest(
            request = {
                Log.d("ololo", "Запрос к API: $query")
                moviesUseCase.invoke(query)
            },
            state = _movieState,
            dispatcher = io
        )
    }
}