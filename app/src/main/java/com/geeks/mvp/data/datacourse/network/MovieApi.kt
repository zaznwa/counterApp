package com.geeks.mvp.data.datacourse.network

import com.geeks.mvp.data.model.movieResponse.MovieApiResponse
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("v1.4/movie/search")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
    ):MovieApiResponse


}