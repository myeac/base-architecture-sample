package com.base.app.domain.exceptions

sealed class DomainException(
    message: String? = null
) : Exception(message) {
    class NetworkErrorException : DomainException(DOMAIN_ERROR_NETWORK)
    class AccessDeniedException : DomainException(DOMAIN_ERROR_ACCESS_DENIED)
    class NotFoundException(val apiMessage: String? = null) : DomainException(apiMessage?: DOMAIN_ERROR_NOT_FOUND)
    class DatabaseException : DomainException(DOMAIN_ERROR_DATA_BASE)
    data class UnknownException(val msg: String) : DomainException(msg)
}

const val DOMAIN_ERROR_GENERIC = "An unexpected error occurred"
const val DOMAIN_ERROR_NETWORK = "Network error occurred"
const val DOMAIN_ERROR_ACCESS_DENIED = "Access denied"
const val DOMAIN_ERROR_NOT_FOUND = "Resource not found"
const val DOMAIN_ERROR_DATA_BASE = "Database error occurred"