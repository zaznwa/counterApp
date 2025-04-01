package com.geeks.mvp.data.repository

import com.geeks.mvp.data.base.BaseRepository
import com.geeks.mvp.data.datacourse.network.MovieApi
import com.geeks.mvp.data.mappers.MovieMapper
import com.geeks.mvp.data.mappers.toDomain
import com.geeks.mvp.domain.model.Example
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.repository.ApiRepository
import com.geeks.mvp.domain.utils.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ApiRepositoryImpl(
    private val api: MovieApi,
    private val io: CoroutineDispatcher,
    private val mapper: MovieMapper,
) : ApiRepository, BaseRepository() {
    override suspend fun searchMovies(
        query: String,
    ): Flow<Either<Throwable, List<MovieEntity>>> = makeRequest {
        api.searchMovies(query = query).movies
            ?.mapNotNull { movieDto ->
                movieDto?.let { mapper.mapToEntity(it) }
            } ?: emptyList()
    }.flowOn(io)


    override suspend fun exampleRequest(): Flow<Either<Throwable, Example>> = makeRequest {
        api.exampleRequest().body()?.toDomain() ?: throw Exception("Ошибка")
    }.flowOn(io)
}
