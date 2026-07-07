package com.base.app.core

sealed class BaseResponse<out T> {

    data class Success<out T>(
        val data: T
    ) : BaseResponse<T>()

    data class SuccessNotFound(
        val message: String
    ) : BaseResponse<Nothing>()

    data class Error(
        val message: String,
        val code: Int? = null
    ) : BaseResponse<Nothing>()
}