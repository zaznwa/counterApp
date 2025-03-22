package com.geeks.mvp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.mvp.domain.repository.MovieRepository
import com.geeks.mvp.data.mappers.MovieMapper
import com.geeks.mvp.domain.model.MovieEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper,
) : ViewModel() {

    private val _movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val movies = _movies.asStateFlow()

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                Log.d("ololo", "Запрос на поиск: $query")
                val response = movieRepository.searchMovies(query)
                Log.d("ololo", "Ответ от API: ${response.docs?.size} фильмов")
                _movies.value = response.docs!!.map { movieMapper.mapToDomain(it!!) }
            } catch (e: Exception) {
                Log.e("ololo", "Ошибка при поиске фильмов", e)
                e.printStackTrace()
            }
        }
    }
}