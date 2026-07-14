package com.base.app.presentation.film_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.base.app.presentation.film_detail.model.FilmRatingUiModel

@Composable
fun FilmRatingSection(
    ratings: List<FilmRatingUiModel>
) {
    if (ratings.isEmpty()) return
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Critic Scores",
            style = MaterialTheme.typography.titleSmall,
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(end = 16.dp),
        ) {
            items(ratings) { rating ->
                RatingCard(rating)
            }
        }
    }
}

@Composable
fun RatingCard(
    rating: FilmRatingUiModel
) {
    val (shortName, brandColor) = when {
        rating.source.contains("Internet Movie Database") -> "IMDb" to Color(0xFFF5C518)
        rating.source.contains("Rotten Tomatoes") -> "Rotten" to Color(0xFFFA320A)
        rating.source.contains("Metacritic") -> "Metacritic" to Color(0xFF66CC33)
        else -> rating.source to Color.White
    }
    Surface(
        color = Color.White.copy(alpha = 0.05f),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, brandColor.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = shortName,
                style = MaterialTheme.typography.labelSmall,
                color = brandColor,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = rating.value,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Black
            )
        }
    }
}