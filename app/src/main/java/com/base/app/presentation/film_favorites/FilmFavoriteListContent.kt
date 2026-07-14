package com.base.app.presentation.film_favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.base.app.presentation.core.UiState
import com.base.app.presentation.film_detail.components.FilmInfoState
import com.base.app.presentation.film_detail.model.FilmUiModel
import com.base.app.presentation.film_favorites.components.FilmFavoriteItem

@Composable
fun FilmFavoriteListContent(
    uiState: UiState<List<FilmUiModel>>,
    onFilmClick: (String) -> Unit,
    onBack: () -> Unit
) {
    val darkBlue = Color(0xFF001325)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "My Favorites",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Movies you've saved to your library",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.5f)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(darkBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.White)
                    }
                }
                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
                    ) {
                        items(uiState.data) { FilmFavoriteItem(it, onFilmClick) }
                    }
                }
                is UiState.SuccessEmpty -> {
                    FilmInfoState(
                        message = "You don't have any favorite films yet.",
                        onBack = onBack,
                        backgroundColor = darkBlue
                    )
                }
                is UiState.Error -> {
                    FilmInfoState(
                        message = uiState.throwable.message ?: "An error occurred",
                        onBack = onBack,
                        backgroundColor = darkBlue,
                        isError = true
                    )
                }
                else -> Unit
            }
        }
    }
}