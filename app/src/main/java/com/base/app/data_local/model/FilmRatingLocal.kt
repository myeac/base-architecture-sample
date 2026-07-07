package com.base.app.data_local.model

import kotlinx.serialization.Serializable

@Serializable
data class FilmRatingLocal(
    val source: String,
    val value: String
)