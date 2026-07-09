package com.base.app.presentation.film_detail.model

import com.base.app.domain.model.FilmRatingModel

data class FilmRatingUiModel(
    val source: String,
    val value: String,
)

fun List<FilmRatingUiModel>.toDomain() = map { it.toDomain() }

fun FilmRatingUiModel.toDomain() = FilmRatingModel(
    source = this.source,
    value = this.value,
)