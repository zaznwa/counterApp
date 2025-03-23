package com.geeks.mvp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.mvp.domain.repository.MovieRepository
import com.geeks.mvp.data.mappers.MovieMapper
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.usecases.MovieUserCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val moviesUseCase: MovieUserCase,
) : ViewModel() {

    private val _movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val movies = _movies.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                Log.d("ololo", "Запрос на поиск: $query")
                _movies.value = moviesUseCase(query)
            } catch (e: Exception) {
                Log.e("ololo", "Ошибка при поиске фильмов", e)
                _errorMessage.value = "Сбой в сервере. Ошибка: ${e.localizedMessage}"
            }
        }
    }
}