package com.base.app.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.base.app.intent.HomeNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigator: HomeNavigator,
    viewmodel: HomeViewModel = koinViewModel()
) {
    val searchText by viewmodel.searchText.collectAsStateWithLifecycle()
    val searchType by viewmodel.searchType.collectAsStateWithLifecycle()

    HomeFilmContent(
        searchText = searchText,
        searchType = searchType,
        onSearchTextChanged = viewmodel::onSearchTextChanged,
        onSearchTypeChanged = viewmodel::onSearchTypeChanged,
        onSearchClicked = { navigator.navigateToDetail(searchText, searchType) },
        onGoToFavorites = { navigator.navigateToFavorites() }
    )
}