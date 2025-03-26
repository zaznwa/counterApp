package com.geeks.mvp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.mvp.domain.repository.MovieRepository
import com.geeks.mvp.data.mappers.MovieMapper
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.usecases.MovieUserCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class MovieViewModel(
    private val moviesUseCase: MovieUserCase,
    private val mainDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val movies = _movies.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

   suspend fun searchMovies(query: String) {
        withContext(ioDispatcher) {
            try {
                Log.d("ololo", "Запрос на поиск: $query")
                _movies.value = moviesUseCase(query)
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка: ${e.localizedMessage}"
            }
        }
    }

}