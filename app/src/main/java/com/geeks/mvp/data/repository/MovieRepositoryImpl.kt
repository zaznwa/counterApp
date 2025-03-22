package com.geeks.mvp.data.repository

import com.geeks.mvp.data.datacourse.network.MovieApi
import com.geeks.mvp.domain.repository.MovieRepository
import com.geeks.mvp.data.model.models.MovieApiResponse

class MovieRepositoryImpl(
    private val api: MovieApi,
) : MovieRepository {
    override suspend fun searchMovies(query: String): MovieApiResponse {
        return api.searchMovies(query)
    }

}