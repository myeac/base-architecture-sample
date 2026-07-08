package com.base.app.domain.core

import com.base.app.domain.exceptions.toDomainError

/**
 * For UseCases that require parameters.
 */
abstract class BaseUseCase<in Params, out DOMAIN> {
    abstract suspend fun execute(params: Params): DOMAIN

    suspend operator fun invoke(params: Params): DOMAIN {
        return try {
            execute(params)
        } catch (e: Throwable) {
            throw e.toDomainError()
        }
    }
}

/**
 * For UseCases that DO NOT require parameters.
 */
abstract class BaseNoParamsUseCase<out DOMAIN> {
    abstract suspend fun execute(): DOMAIN

    suspend operator fun invoke(): DOMAIN {
        return try {
            execute()
        } catch (e: Throwable) {
            throw e.toDomainError()
        }
    }
}