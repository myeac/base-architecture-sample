package com.base.app.domain.model

import com.base.app.data_local.model.FilmRatingLocal
import com.base.app.presentation.film_detail.model.FilmRatingUiModel

data class FilmRatingModel(
    val source: String,
    val value: String,
)

fun List<FilmRatingModel>.toLocal() = map { it.toLocal() }

fun FilmRatingModel.toLocal() = FilmRatingLocal(
    source = source,
    value = value,
)

fun List<FilmRatingModel>.toUi() = map { it.toUi() }

fun FilmRatingModel.toUi() = FilmRatingUiModel(
    source = this.source,
    value = this.value,
)