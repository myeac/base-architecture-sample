package com.base.app.presentation.film_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun FilmPosterOverlay(
    posterUrl: String, backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to backgroundColor,
                            0.05f to Color.Transparent,
                            0.95f to Color.Transparent,
                            1.0f to backgroundColor,
                        )
                    )
                )
        )
    }
}