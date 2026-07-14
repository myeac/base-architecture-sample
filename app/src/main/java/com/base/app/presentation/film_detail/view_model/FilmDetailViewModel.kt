package com.base.app.presentation.film_detail.view_model

import com.base.app.domain.model.toUi
import com.base.app.domain.use_case.GetFilmByIdUseCase
import com.base.app.domain.use_case.GetFilmByTitleUseCase
import com.base.app.domain.use_case.IsFilmFavoriteUseCase
import com.base.app.domain.use_case.RemoveFavoriteFilmUseCase
import com.base.app.domain.use_case.SaveFavoriteFilmUseCase
import com.base.app.domain.use_case.SearchFavoriteByIdUseCase
import com.base.app.presentation.core.BaseViewModel
import com.base.app.presentation.core.UiState
import com.base.app.presentation.film_detail.model.FilmUiModel
import com.base.app.presentation.film_detail.model.toDomain
import com.base.app.presentation.home.FilmSearchTypeUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FilmDetailViewModel(
    private val getFilmByTitleUseCase: GetFilmByTitleUseCase,
    private val getFilmByIdUseCase: GetFilmByIdUseCase,
    private val isFilmFavoriteUseCase: IsFilmFavoriteUseCase,
    private val saveFavoriteFilmUseCase: SaveFavoriteFilmUseCase,
    private val removeFavoriteFilmUseCase: RemoveFavoriteFilmUseCase,
    private val searchFavoriteByIdUseCase: SearchFavoriteByIdUseCase,
) : BaseViewModel() {

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    private val _searchFilmState = MutableStateFlow<UiState<FilmUiModel>>(UiState.Idle)
    val searchFilmState = _searchFilmState.asStateFlow()

    fun loadFilm(
        parameter: String,
        searchType: FilmSearchTypeUiModel
    ) {
        when (searchType) {
            FilmSearchTypeUiModel.SEARCH_BY_TITLE -> searchFilmByTitle(parameter)
            FilmSearchTypeUiModel.SEARCH_BY_ID -> searchFilmById(parameter)
            FilmSearchTypeUiModel.SEARCH_FROM_FAVORITES -> searchFavouriteById(parameter)
        }
    }

    fun searchFilmByTitle(
        title: String
    ) {
        if (title.isBlank()) {
            resetState(_searchFilmState)
            return
        }
        executeUseCase(
            uiStateFlow = _searchFilmState,
            call = { getFilmByTitleUseCase(title) },
            mapper = { it.toUi() },
            onSuccess = { uiModel -> checkFavoriteStatus(uiModel.imdbID) }
        )
    }

    fun searchFilmById(
        id: String
    ) {
        if (id.isBlank()) {
            resetState(_searchFilmState)
            return
        }
        executeUseCase(
            uiStateFlow = _searchFilmState,
            call = { getFilmByIdUseCase(id) },
            mapper = { it.toUi() },
            onSuccess = { uiModel -> checkFavoriteStatus(uiModel.imdbID) }
        )
    }

    fun checkFavoriteStatus(
        imdbId: String
    ) {
        executeLocalCall(
            localCall = { isFilmFavoriteUseCase(imdbId) },
            onSuccess = { _isFavorite.value = it },
        )
    }

    fun searchFavouriteById(
        imdbId: String
    ) {
        collectFlow(
            uiStateFlow = _searchFilmState,
            flowCall = { searchFavoriteByIdUseCase(imdbId) },
            mapper = { it.toUi() },
            onSuccess = { uiModel -> checkFavoriteStatus(uiModel.imdbID) }
        )
    }

    fun toggleFavorite(
        film: FilmUiModel
    ) {
        executeLocalCall(
            localCall = {
                if (_isFavorite.value)
                    removeFavoriteFilmUseCase(film.imdbID)
                else
                    saveFavoriteFilmUseCase(film.toDomain())
            },
            onSuccess = { _isFavorite.value = !_isFavorite.value },
        )
    }

    fun clearSearch() {
        resetState(_searchFilmState)
        _isFavorite.value = false
    }
}