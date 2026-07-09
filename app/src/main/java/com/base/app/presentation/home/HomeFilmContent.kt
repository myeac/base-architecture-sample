package com.base.app.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeFilmContent(
    searchText: String,
    searchType: FilmSearchTypeUiModel,
    onSearchTextChanged: (String) -> Unit,
    onSearchTypeChanged: (FilmSearchTypeUiModel) -> Unit,
    onSearchClicked: () -> Unit,
    onGoToFavorites: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChanged,
            label = { Text("Enter the Film title or ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = searchType == FilmSearchTypeUiModel.SEARCH_BY_TITLE,
                onClick = { onSearchTypeChanged(FilmSearchTypeUiModel.SEARCH_BY_TITLE) }
            )
            Text("By Title")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = searchType == FilmSearchTypeUiModel.SEARCH_BY_ID,
                onClick = { onSearchTypeChanged(FilmSearchTypeUiModel.SEARCH_BY_ID) }
            )
            Text("By ID")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSearchClicked,
            enabled = searchText.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
            content = { Text("Find Film") }
        )

        Spacer(modifier = Modifier.height(4.dp))

        Button(
            onClick = onGoToFavorites,
            modifier = Modifier.fillMaxWidth(),
            content = { Text("View Favorites List") }
        )
    }
}