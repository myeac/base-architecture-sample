package com.base.app.feature.core

import com.base.app.domain.exceptions.DomainException

sealed class UiState<out UI> {
    data object Idle : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<out UI>(val data: UI) : UiState<UI>()
    data class SuccessEmpty(val message: String) : UiState<Nothing>()
    data class Error(val throwable: DomainException) : UiState<Nothing>()
}