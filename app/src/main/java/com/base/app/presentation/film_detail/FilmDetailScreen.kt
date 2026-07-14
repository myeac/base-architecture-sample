package com.base.app.presentation.film_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.base.app.domain.model.toUi
import com.base.app.presentation.core.UiState
import com.base.app.presentation.film_detail.view_model.FilmDetailViewModel
import com.base.app.presentation.home.FilmSearchTypeUiModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilmDetailScreen(
    parameter: String,
    searchType: FilmSearchTypeUiModel,
    viewModel: FilmDetailViewModel = koinViewModel()
) {

    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()
    val filmState by viewModel.searchFilmState.collectAsStateWithLifecycle()

    LaunchedEffect(parameter, searchType) {
        viewModel.loadFilm(parameter, searchType)
    }

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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.message,
                    color = Color.White
                )
            }
        }
        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.throwable.message ?: "An error occurred",
                    color = Color.Red
                )
            }
        }
        else -> Unit
    }
}