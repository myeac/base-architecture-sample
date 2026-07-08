package com.base.app.domain.exceptions

import com.base.app.data_local.core.LocalException
import com.base.app.data_remote.core.ApiException
import com.base.app.data_remote.core.SuccessNotFoundException
import okio.IOException

fun Throwable.toDomainError(): DomainException {
    return when (this) {
        is SuccessNotFoundException -> DomainException.NotFoundException(this.message)
        is ApiException -> {
            when (this.code) {
                401 -> DomainException.AccessDeniedException()
                else -> DomainException.NetworkErrorException()
            }
        }
        is LocalException -> DomainException.DatabaseException()
        is IOException -> DomainException.NetworkErrorException()
        else -> DomainException.UnknownException(this.message ?: DOMAIN_ERROR_GENERIC)
    }
}