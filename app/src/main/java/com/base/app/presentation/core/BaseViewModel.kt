package com.base.app.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.app.domain.exceptions.DomainException
import com.base.app.domain.exceptions.toDomainError
import com.base.app.feature.core.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _errorEvents = MutableSharedFlow<DomainException>()
    val errorEvents = _errorEvents.asSharedFlow()

    /**
     * Executes a Use Case and manages the UiState lifecycle.
     * It automatically handles Loading, Success, SuccessEmpty, and Error states.
     */
    protected fun <T> executeUseCase(
        uiStateFlow: MutableStateFlow<UiState<T>>,
        call: suspend () -> T
    ) {
        viewModelScope.launch {
            uiStateFlow.value = UiState.Loading
            try {
                val result = call()
                handleSuccess(uiStateFlow, result)
            } catch (exception: Exception) {
                handleError(uiStateFlow, exception)
            }
        }
    }

    protected fun <T> handleSuccess(
        uiStateFlow: MutableStateFlow<UiState<T>>,
        result: T
    ) {
        if (result is List<*> && result.isEmpty()) {
            uiStateFlow.value = UiState.SuccessEmpty("No results found")
        } else {
            uiStateFlow.value = UiState.Success(result)
        }
    }

    protected fun <T> handleError(
        uiStateFlow: MutableStateFlow<UiState<T>>,
        exception: Exception
    ) {
        val domainError = exception as? DomainException ?: exception.toDomainError()
        if (domainError is DomainException.NotFoundException) {
            uiStateFlow.value = UiState.SuccessEmpty(domainError.apiMessage ?: "Not found")
        } else {
            uiStateFlow.value = UiState.Error(domainError)
        }
    }

    protected fun <T> executeLocalCall(
        localCall: suspend () -> T,
        onSuccess: (T) -> Unit = {},
        onError: ((DomainException) -> Unit)? = null
    ) {
        viewModelScope.launch {
            try {
                val result = localCall()
                onSuccess(result)
            } catch (exception: Exception) {
                val domainError = (exception as? DomainException) ?: exception.toDomainError()
                _errorEvents.emit(domainError)
                onError?.invoke(domainError)
            }
        }
    }

    protected fun <T> resetState(
        uiStateFlow: MutableStateFlow<UiState<T>>
    ) {
        uiStateFlow.value = UiState.Idle
    }
}