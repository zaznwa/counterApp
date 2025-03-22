package com.geeks.mvp.data.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiResponse(
    @SerialName("docs")
    var docs: List<Doc?>?
) {
    @Serializable
    data class Doc(
        @SerialName("name")
        var name: String?,  // Название фильма
        @SerialName("poster")
        var poster: Poster?,  // Постер
        @SerialName("description")
        var description: String?  // Описание
    )
        @Serializable
        data class Poster(
            @SerialName("previewUrl")
            var previewUrl: String?,
            @SerialName("url")
            var url: String?
        )


}

