package com.geeks.mvp.data.repository

import com.geeks.mvp.data.base.BaseRepository
import com.geeks.mvp.data.datacourse.network.MovieApi
import com.geeks.mvp.data.mappers.MovieMapper
import com.geeks.mvp.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val io: CoroutineDispatcher,
    private val mapper: MovieMapper,
) : MovieRepository, BaseRepository() {
    override suspend fun searchMovies(
        query: String,
    ) = makeRequest {
        api.searchMovies(query = query).movies
            ?.mapNotNull { movieDto ->
                movieDto?.let { mapper.mapToEntity(it) }
            } ?: emptyList()
    }.flowOn(io)


    override suspend fun exampleRequest() = makeRequest {
        api.exampleRequest().toDomain()
    }.flowOn(io)
}
