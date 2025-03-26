package com.geeks.mvp.domain.repository

import com.geeks.mvp.data.model.MovieApiResponse
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.utils.Either
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun searchMovies(query: String): Flow<Either<Throwable, List<MovieEntity>>>
}