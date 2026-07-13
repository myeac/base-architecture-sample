package com.base.app.presentation.film_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        FilmDataValueStructure("Director", film.director)
        FilmDataValueStructure("Writer", film.writer)
        FilmDataValueStructure("Actors", film.actors)
        FilmDataValueStructure("Language", film.language)
        FilmDataValueStructure("Country", film.country)
        FilmDataValueStructure("Details", film.awards)
    }
}

@Composable
fun FilmDataValueStructure(
    label: String,
    value: String,
) {
    if (value.isBlank() || value == "N/A") return

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 6.dp
            ),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = "$label: ",
            modifier = Modifier.width(90.dp),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.5f),
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = value,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.9f),
        )
    }
}