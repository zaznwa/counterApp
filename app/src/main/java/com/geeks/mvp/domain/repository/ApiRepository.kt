package com.geeks.mvp.domain.repository

import com.geeks.mvp.domain.model.Example
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.utils.Either
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    suspend fun searchMovies(query: String): Flow<Either<Throwable, List<MovieEntity>>>

    suspend fun exampleRequest(): Flow<Either<Throwable, Example>>
}