package com.geeks.mvp.data.mappers

import com.geeks.mvp.data.model.movieResponse.MovieApiResponse
import com.geeks.mvp.domain.model.MovieEntity

class MovieMapper {
    fun mapToEntity(movie: MovieApiResponse.MovieList): MovieEntity {
        return MovieEntity(
            name = movie.name ?: "",
            description = movie.description ?: "",
            posterUrl = movie.poster?.previewUrl ?: "",
        )
    }

}