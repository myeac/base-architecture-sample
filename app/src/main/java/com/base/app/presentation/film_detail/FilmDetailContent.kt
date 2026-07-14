package com.base.app.presentation.film_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.base.app.presentation.film_detail.components.FilmDataValueRatingSection
import com.base.app.presentation.film_detail.components.FilmDataValueSection
import com.base.app.presentation.film_detail.components.FilmHeaderOverlay
import com.base.app.presentation.film_detail.components.FilmInfoSection
import com.base.app.presentation.film_detail.components.FilmPosterOverlay
import com.base.app.presentation.film_detail.components.FilmRatingSection
import com.base.app.presentation.film_detail.model.FilmUiModel
import com.movies.app.R

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun FilmDetailContent(
    film: FilmUiModel,
    isFavorite: Boolean,
    onFavoriteToggle: (FilmUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val darkBlue = Color(0xFF001325)
    val darkRed = Color(0xFFB60000)
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = darkBlue,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onFavoriteToggle(film) },
                containerColor = if (isFavorite) darkRed else Color.White,
                contentColor = if (isFavorite) Color.White else darkRed,
                shape = MaterialTheme.shapes.large,
            ) {
                Icon(
                    painter = if (isFavorite)
                        painterResource(id = R.drawable.ic_add)
                    else
                        painterResource(id = R.drawable.ic_add),
                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { _ ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(darkBlue)
                .verticalScroll(scrollState)
        ) {
            FilmHeaderOverlay(film.imdbID, film.imdbRating)
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
}