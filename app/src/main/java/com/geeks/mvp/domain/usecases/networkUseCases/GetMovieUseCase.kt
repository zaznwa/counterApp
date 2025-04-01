package com.geeks.mvp.domain.usecases.networkUseCases

import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.repository.ApiRepository
import com.geeks.mvp.domain.utils.Either
import kotlinx.coroutines.flow.Flow

class GetMovieUseCase(
    private val apiRepository: ApiRepository,
//    private val movieMapper: MovieMapper,
) {
    suspend operator fun invoke(
        query: String,
    ): Flow<Either<Throwable, List<MovieEntity>>> {
        return apiRepository.searchMovies(query)
    }
}