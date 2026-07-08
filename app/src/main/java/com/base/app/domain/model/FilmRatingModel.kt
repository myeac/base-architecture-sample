package com.base.app.domain.model

import com.base.app.data_local.model.FilmRatingLocal

data class FilmRatingModel(
    val source: String,
    val value: String,
)

fun List<FilmRatingModel>.toLocal() = map { it.toLocal() }

fun FilmRatingModel.toLocal() = FilmRatingLocal(
    source = source,
    value = value,
)