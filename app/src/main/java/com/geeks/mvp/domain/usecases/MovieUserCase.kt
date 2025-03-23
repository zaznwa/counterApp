package com.geeks.mvp.domain.usecases

import com.geeks.mvp.data.mappers.MovieMapper
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.repository.MovieRepository

class MovieUserCase(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper,
) {
    suspend operator fun invoke(query: String): List<MovieEntity> {
        val response = movieRepository.searchMovies(query)
        return response.docs?.map { movieMapper.mapToDomain(it!!) } ?: emptyList()
    }
}