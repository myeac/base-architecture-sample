package com.base.app.presentation.film_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.base.app.presentation.film_detail.components.FilmDataValueRatingSection
import com.base.app.presentation.film_detail.components.FilmDataValueSection
import com.base.app.presentation.film_detail.components.FilmHeaderOverlay
import com.base.app.presentation.film_detail.components.FilmInfoSection
import com.base.app.presentation.film_detail.components.FilmPosterOverlay
import com.base.app.presentation.film_detail.components.FilmRatingSection
import com.base.app.presentation.film_detail.model.FilmUiModel

@Composable
fun FilmDetailContent(
    film: FilmUiModel,
    isFavorite: Boolean,
    onFavoriteToggle: (FilmUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val darkBlue = Color(0xFF001325)
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(darkBlue)
            .verticalScroll(scrollState)
    ) {
        FilmHeaderOverlay(film)
        FilmPosterOverlay(film.posterUrl, darkBlue)
        FilmInfoSection(film)
        Spacer(modifier = Modifier.height(8.dp))
        FilmDataValueSection(film)
        Spacer(modifier = Modifier.height(8.dp))
        FilmRatingSection(film.ratings)
        Spacer(modifier = Modifier.height(8.dp))
        FilmDataValueRatingSection(film)
        Spacer(modifier = Modifier.height(16.dp))
    }
}