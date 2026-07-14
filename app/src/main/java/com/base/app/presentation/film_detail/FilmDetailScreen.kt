package com.base.app.presentation.film_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.base.app.presentation.core.UiState
import com.base.app.presentation.film_detail.components.FilmInfoState
import com.base.app.presentation.film_detail.view_model.FilmDetailViewModel
import com.base.app.presentation.home.FilmSearchTypeUiModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilmDetailScreen(
    parameter: String,
    searchType: FilmSearchTypeUiModel,
    onBack: () -> Unit,
    viewModel: FilmDetailViewModel = koinViewModel()
) {

    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()
    val filmState by viewModel.searchFilmState.collectAsStateWithLifecycle()

    LaunchedEffect(parameter, searchType) {
        viewModel.loadFilm(parameter, searchType)
    }

    val darkBlue = Color(0xFF001325)

    when (val state = filmState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Black)
            }
        }
        is UiState.Success -> {
            FilmDetailContent(
                film = state.data,
                isFavorite = isFavorite,
                onFavoriteToggle = { viewModel.toggleFavorite(it) }
            )
        }
        is UiState.SuccessEmpty -> {
            FilmInfoState(
                message = state.message,
                onBack = onBack,
                backgroundColor = darkBlue
            )
        }
        is UiState.Error -> {
            FilmInfoState(
                message = state.throwable.message ?: "An unexpected error occurred",
                onBack = onBack,
                backgroundColor = darkBlue,
                isError = true
            )
        }
        else -> Unit
    }
}