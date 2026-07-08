package com.base.app.presentation

import androidx.lifecycle.viewModelScope
import com.base.app.domain.model.FilmModel
import com.base.app.domain.model.FilmSortStateModel
import com.base.app.domain.use_case.GetListFavoriteFilmsUseCase
import com.base.app.domain.use_case.RemoveFavoriteFilmUseCase
import com.base.app.domain.use_case.SearchInFavoritesByNameUseCase
import com.base.app.feature.core.UiState
import com.base.app.presentation.core.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoriteFilmsViewModel(
    private val getListFavoriteFilmsUseCase: GetListFavoriteFilmsUseCase,
    private val searchInFavoritesByNameUseCase: SearchInFavoritesByNameUseCase,
    private val removeFavoriteFilmUseCase: RemoveFavoriteFilmUseCase,
) : BaseViewModel() {

    private val _favoriteFilmsState = MutableStateFlow<UiState<List<FilmModel>>>(UiState.Idle)
    val favoriteFilmsState = _favoriteFilmsState.asStateFlow()

    private val _sortState = MutableStateFlow(FilmSortStateModel.NONE)
    val sortState = _sortState.asStateFlow()

    private var collectionJob: Job? = null

    init {
        loadFavorites(FilmSortStateModel.NONE)
    }

    fun loadFavorites(
        sort: FilmSortStateModel
    ) {
        _sortState.value = sort
        collectionJob?.cancel()

        collectionJob = viewModelScope.launch {
            getListFavoriteFilmsUseCase(sort)
                .onStart { _favoriteFilmsState.value = UiState.Loading }
                .onEach { handleSuccess(_favoriteFilmsState, it) }
                .catch { handleError(_favoriteFilmsState, it as Exception) }
                .collect { }
        }
    }

    fun searchFavorites(
        query: String
    ) {
        if (query.isBlank()) {
            loadFavorites(_sortState.value)
            return
        }

        collectionJob?.cancel()
        collectionJob = viewModelScope.launch {
            searchInFavoritesByNameUseCase(query)
                .onStart { _favoriteFilmsState.value = UiState.Loading }
                .onEach { handleSuccess(_favoriteFilmsState, it) }
                .catch { handleError(_favoriteFilmsState, it as Exception) }
                .collect { }
        }
    }

    fun removeFavorite(
        id: String
    ) {
        executeLocalCall(
            localCall = { removeFavoriteFilmUseCase(id) }
        )
    }
}