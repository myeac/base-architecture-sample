package com.base.app.data_remote.dto

import com.base.app.domain.model.FilmRatingModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmRatingDTO(
    @SerialName("Source")
    val source: String? = null,

    @SerialName("Value")
    val value: String? = null
)

fun List<FilmRatingDTO>.toDomain() = map { it.toDomain() }

fun FilmRatingDTO.toDomain() = FilmRatingModel(
    source = source ?: "",
    value = value ?: ""
)