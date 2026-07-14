package com.base.app.presentation.film_favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.base.app.presentation.film_favorites.view_model.FavoriteFilmsViewModel
import com.base.app.presentation.home.model.FilmSearchTypeUiModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilmFavoriteListScreen(
    onNavigateToDetail: (String, FilmSearchTypeUiModel) -> Unit,
    onBack: () -> Unit,
    viewModel: FavoriteFilmsViewModel = koinViewModel()
) {
    val favoriteState by viewModel.favoriteFilmsState.collectAsStateWithLifecycle()

    FilmFavoriteListContent(
        uiState = favoriteState,
        onFilmClick = { onNavigateToDetail(it, FilmSearchTypeUiModel.SEARCH_FROM_FAVORITES) },
        onBack = onBack,
    )
}