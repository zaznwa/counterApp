package com.geeks.mvp.data.datacourse.network

import com.geeks.mvp.data.model.example.ExampleDto
import com.geeks.mvp.data.model.movieResponse.MovieApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface MovieApi {
    @GET("v1.4/movie/search")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
    ): MovieApiResponse


    @GET("v1.4/movie/search")
    suspend fun exampleRequest(

    ): Response<ExampleDto>
}