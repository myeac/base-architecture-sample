package com.base.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.base.app.presentation.home.model.FilmSearchTypeUiModel
import com.base.app.presentation.theme.darkYellow
import com.movies.app.R

@Composable
fun HomeFilmContent(
    searchText: String,
    searchType: FilmSearchTypeUiModel,
    onSearchTextChanged: (String) -> Unit,
    onSearchTypeChanged: (FilmSearchTypeUiModel) -> Unit,
    onSearchClicked: () -> Unit,
    onGoToFavorites: () -> Unit,
) {
    val darkBlue = Color(0xFF001325)
    val appYellow = Color(0xFFF5C518)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Text(
                text = "Movie Explorer",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Discover movies by Title or IMDb ID",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.6f)
            )
        }

        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChanged,
            placeholder = { Text("Search movie...", color = Color.White.copy(alpha = 0.3f)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color.White
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = appYellow,
                unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
                cursorColor = appYellow,
                focusedContainerColor = Color.White.copy(alpha = 0.05f)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Search Method",
            style = MaterialTheme.typography.labelMedium,
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SearchOptionChip(
                label = "By Title",
                isSelected = searchType == FilmSearchTypeUiModel.SEARCH_BY_TITLE,
                onClick = { onSearchTypeChanged(FilmSearchTypeUiModel.SEARCH_BY_TITLE) }
            )
            SearchOptionChip(
                label = "By ID",
                isSelected = searchType == FilmSearchTypeUiModel.SEARCH_BY_ID,
                onClick = { onSearchTypeChanged(FilmSearchTypeUiModel.SEARCH_BY_ID) }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onSearchClicked,
            enabled = searchText.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = appYellow,
                contentColor = Color.Black
            )
        ) {
            Text("Find Movie", fontWeight = FontWeight.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onGoToFavorites,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
        ) {
            Text("My Favorites Library")
        }
    }
}


@Composable
fun SearchOptionChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = { Text(label) },
        shape = RoundedCornerShape(12.dp),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = darkYellow.copy(alpha = 0.15f),
            selectedLabelColor = darkYellow,
            labelColor = Color.White.copy(alpha = 0.5f),
            containerColor = Color.Transparent
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = if (isSelected) darkYellow else Color.White.copy(alpha = 0.2f),
            enabled = true,
            selected = isSelected
        )
    )
}