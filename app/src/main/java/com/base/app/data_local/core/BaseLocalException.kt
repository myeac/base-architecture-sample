package com.base.app.data_local.core

class LocalException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)