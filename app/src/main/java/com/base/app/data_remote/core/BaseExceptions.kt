package com.base.app.data_remote.core

/** Exception for OMDb "Response: False" cases (e.g. Movie not found) */
class SuccessNotFoundException(
    message: String
) : Exception(message)

/** Exception for Technical/HTTP errors (e.g. 401 Unauthorized, 500 Server Error) */
class ApiException(
    message: String,
    val code: Int? = null
) : Exception(message)