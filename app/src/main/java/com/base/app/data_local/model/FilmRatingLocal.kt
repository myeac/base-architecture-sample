package com.base.app.data_local.model

import com.base.app.domain.model.FilmRatingModel
import kotlinx.serialization.Serializable

@Serializable
data class FilmRatingLocal(
    val source: String,
    val value: String
)

fun List<FilmRatingLocal>.toDomain() = map { it.toDomain() }

fun FilmRatingLocal.toDomain() = FilmRatingModel(
    source = source,
    value = value,
)