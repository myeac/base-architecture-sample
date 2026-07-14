package com.base.app.intent

import com.base.app.presentation.home.model.FilmSearchTypeUiModel
import kotlinx.serialization.Serializable

@Serializable
object HomeDestination

@Serializable
object FilmFavoritesDestination

@Serializable
data class FilmDetailDestination(
    val parameter: String,
    val searchType: FilmSearchTypeUiModel
)