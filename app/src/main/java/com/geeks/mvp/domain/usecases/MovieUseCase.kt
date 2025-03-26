package com.geeks.mvp.domain.usecases

import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.repository.MovieRepository
import com.geeks.mvp.domain.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieUseCase(
    private val movieRepository: MovieRepository,
//    private val movieMapper: MovieMapper,
) {
    suspend operator fun invoke(
        query: String,
    ): Flow<Either<Throwable, List<MovieEntity>>> {
        return movieRepository.searchMovies(query)
    }
}