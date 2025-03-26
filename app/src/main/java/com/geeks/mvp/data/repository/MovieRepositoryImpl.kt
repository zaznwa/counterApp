package com.geeks.mvp.data.repository

import android.util.Log
import com.geeks.mvp.data.datacourse.network.MovieApi
import com.geeks.mvp.domain.repository.MovieRepository
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.utils.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val ioDispatcher: CoroutineDispatcher,
) : MovieRepository {
    override suspend fun searchMovies(
        query: String,
    ): Flow<Either<Throwable, List<MovieEntity>>> {
        return flow {
            try {
                Log.d("ololo", "Start request: $query")
                val response = api.searchMovies(query)
                Log.d("ololo", "Response code: ${response.code()}")
                if (response.isSuccessful) {
                    val docs = response.body()?.docs ?: emptyList()
                    val movies = docs.mapNotNull { doc ->
                        doc?.let {
                            MovieEntity(
                                name = it.name ?: "Без названия",
                                description = it.description ?: "Нет описания",
                                posterUrl = it.poster?.url ?: it.poster?.previewUrl ?: ""
                            )
                        }
                    } ?: emptyList()

                    Log.d("ololo", "Movies received: ${movies.size}")
                    emit(Either.Right(movies))
                } else {
                    Log.e("ololo", "Error: ${response.errorBody()?.string()}")
                    emit(Either.Left(Exception("Ошибка: ${response.code()}")))
                }
            } catch (e: Exception) {
                Log.e("ololo", "Exception: ${e.message}", e)
                emit(Either.Left(e))
            }
        }.flowOn(ioDispatcher)
    }
}
