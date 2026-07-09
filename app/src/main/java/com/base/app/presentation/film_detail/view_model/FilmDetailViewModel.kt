package com.base.app.presentation.film_detail.view_model

import com.base.app.domain.model.FilmModel
import com.base.app.domain.use_case.GetFilmByIdUseCase
import com.base.app.domain.use_case.GetFilmByTitleUseCase
import com.base.app.domain.use_case.IsFilmFavoriteUseCase
import com.base.app.domain.use_case.RemoveFavoriteFilmUseCase
import com.base.app.domain.use_case.SaveFavoriteFilmUseCase
import com.base.app.presentation.core.BaseViewModel
import com.base.app.presentation.core.UiState
import com.base.app.presentation.film_detail.model.FilmUiModel
import com.base.app.presentation.film_detail.model.toDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FilmDetailViewModel(
    private val getFilmByTitleUseCase: GetFilmByTitleUseCase,
    private val getFilmByIdUseCase: GetFilmByIdUseCase,
    private val isFilmFavoriteUseCase: IsFilmFavoriteUseCase,
    private val saveFavoriteFilmUseCase: SaveFavoriteFilmUseCase,
    private val removeFavoriteFilmUseCase: RemoveFavoriteFilmUseCase,
) : BaseViewModel() {

    private val _titleState = MutableStateFlow<UiState<FilmModel>>(UiState.Idle)
    val titleState = _titleState.asStateFlow()

    private val _idState = MutableStateFlow<UiState<FilmModel>>(UiState.Idle)
    val idState = _idState.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun searchFilmByTitle(
        title: String
    ) {
        if (title.isBlank()) {
            resetState(_titleState)
            return
        }
        executeUseCase(
            uiStateFlow = _titleState,
            call = { getFilmByTitleUseCase(title) }
        )
    }

    fun searchFilmById(
        id: String
    ) {
        if (id.isBlank()) {
            resetState(_idState)
            return
        }
        executeUseCase(
            uiStateFlow = _idState,
            call = { getFilmByIdUseCase(id) }
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
        resetState(_titleState)
        resetState(_idState)
        _isFavorite.value = false
    }
}