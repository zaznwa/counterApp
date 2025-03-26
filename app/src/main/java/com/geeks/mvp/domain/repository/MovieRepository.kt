package com.geeks.mvp.domain.repository

import com.geeks.mvp.data.model.MovieApiResponse

interface MovieRepository {
    suspend fun searchMovies(query: String): MovieApiResponse
}