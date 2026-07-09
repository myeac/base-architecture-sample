package com.base.app.presentation.home

import com.base.app.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : BaseViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _searchType = MutableStateFlow(FilmSearchTypeUiModel.SEARCH_BY_TITLE)
    val searchType = _searchType.asStateFlow()

    fun onSearchTextChanged(
        newText: String
    ) {
        _searchText.value = newText
    }

    fun onSearchTypeChanged(
        type: FilmSearchTypeUiModel
    ) {
        _searchType.value = type
    }
}