package com.geeks.mvp.data.model.movieResponse


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiResponse(
    @SerializedName("docs")
    var movies: List<MovieList?>?
) {
    @Serializable
    data class MovieList(
        @SerializedName("name")
        var name: String?,  // Название фильма
        @SerializedName("poster")
        var poster: Poster?,  // Постер
        @SerializedName("description")
        var description: String?  // Описание
    )
        @Serializable
        data class Poster(
            @SerializedName("previewUrl")
            var previewUrl: String?,
            @SerializedName("url")
            var url: String?
        )


}

