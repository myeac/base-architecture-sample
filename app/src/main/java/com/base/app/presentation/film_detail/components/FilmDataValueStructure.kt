package com.base.app.presentation.film_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.base.app.presentation.film_detail.model.FilmUiModel

@Composable
fun FilmDataValueSection(
    film: FilmUiModel,
) {
    FilmDataValueStructure("Director", film.director)
    FilmDataValueStructure("Writer", film.writer)
    FilmDataValueStructure("Actors", film.actors)
    FilmDataValueStructure("Language", film.language)
    FilmDataValueStructure("Country", film.country)
    FilmDataValueStructure("Details", film.awards)
}

@Composable
fun FilmDataValueStructure(
    type: String = "type",
    value: String = "value"
) {
    Row(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 4.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "$type: ",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.9f),
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.9f),
        )
    }
}