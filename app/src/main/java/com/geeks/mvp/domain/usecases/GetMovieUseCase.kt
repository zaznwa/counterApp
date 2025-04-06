package com.geeks.mvp.domain.usecases

import android.util.Log
import com.geeks.mvp.data.mappers.MovieMapper
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.domain.repository.ApiRepository
import com.geeks.mvp.presentation.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class GetMovieUseCase(
    private val apiRepository: ApiRepository,
    private val movieMapper: MovieMapper,
) {
    suspend operator fun invoke(
        query: String,
    ): Flow<Either<Throwable, List<MovieEntity>>> {
        Log.d("ololo", "Отправляю запрос с параметрами: query=$query")

        val responseFlow = apiRepository.searchMovies(query)

        responseFlow.collect { result ->
            when (result) {
                is Either.Success -> {
                    Log.d("ololo", "Ответ от API - Movies: ${result.data}")
                }
                is Either.Error -> {
                    Log.e("ololo", "Ошибка: ${result.error.localizedMessage}")
                }
            }
        }

        return responseFlow
    }
}
