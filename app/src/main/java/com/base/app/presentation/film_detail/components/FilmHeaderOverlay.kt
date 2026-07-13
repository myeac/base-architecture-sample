package com.base.app.presentation.film_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.base.app.presentation.film_detail.model.FilmUiModel

@Composable
fun FilmHeaderOverlay(
    film: FilmUiModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "ID: ${film.imdbID}",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White
        )
        Text(
            text = "★ ${film.imdbRating}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Yellow
        )
    }
}