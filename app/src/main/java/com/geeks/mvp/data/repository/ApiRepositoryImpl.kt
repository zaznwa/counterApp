package com.geeks.mvp.data.repository

import android.util.Log
import com.geeks.mvp.data.base.BaseRepository
import com.geeks.mvp.data.datacourse.network.MovieApi
import com.geeks.mvp.data.mappers.MovieMapper
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.repository.ApiRepository
import com.geeks.mvp.presentation.utils.Either
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
        try {
            val response = api.searchMovies(query, page = 1, limit = 10)
            Log.d("ololo", "Ответ от API: ${response.movies}")
            response.movies
                ?.mapNotNull { dto -> dto?.let { mapper.mapToEntity(it) } }
                ?: emptyList()
        } catch (e: Exception) {
            Log.d("ololo", "Ошибка при запросе к API: ${e.localizedMessage}")
            throw e
        }

    }.flowOn(io)
}
