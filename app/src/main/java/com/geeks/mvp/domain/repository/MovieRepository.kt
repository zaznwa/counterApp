package com.geeks.mvp.domain.repository

import com.geeks.mvp.data.model.models.MovieApiResponse

interface MovieRepository {
    suspend fun searchMovies(query: String): MovieApiResponse
}