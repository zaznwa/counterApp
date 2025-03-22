package com.geeks.mvp.data.mappers

import com.geeks.mvp.data.model.models.MovieApiResponse
import com.geeks.mvp.domain.model.MovieEntity

class MovieMapper {
    fun mapToDomain(movie: MovieApiResponse.Doc): MovieEntity {
        return MovieEntity(
            name = movie.name ?: "",
            description = movie.description ?: "",
            posterUrl = movie.poster?.previewUrl ?: "",
        )
    }

}