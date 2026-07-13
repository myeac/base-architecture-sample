package com.base.app.presentation.film_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
fun FilmInfoSection(
    film: FilmUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = film.title,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        val metaData = listOf(
            film.year,
            film.rated,
            film.runtime,
            film.genre,
            film.language,
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            itemsIndexed(metaData) { index, item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
                if (index < metaData.lastIndex) {
                    Text(
                        text = "|",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.3f),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }
        Text(
            text = film.plot,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White.copy(alpha = 0.9f),
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.4f
        )
    }
}