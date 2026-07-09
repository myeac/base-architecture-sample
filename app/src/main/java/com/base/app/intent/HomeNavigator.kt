package com.base.app.intent

import com.base.app.presentation.home.FilmSearchTypeUiModel

interface HomeNavigator {
    fun navigateToDetail(query: String, searchType: FilmSearchTypeUiModel)
    fun navigateToFavorites()
}