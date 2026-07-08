package com.base.app.data_local.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

abstract class BaseLocalHandler {

    protected suspend fun <ENTITY, DOMAIN> safeLocalCall(
        localCall: suspend () -> ENTITY,
        mapper: (ENTITY) -> DOMAIN
    ): DOMAIN {
        return try {
            val result = localCall()
            mapper(result)
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw LocalException("Database error: ${exception.message}", exception)
        }
    }

    protected suspend fun <DOMAIN> safeLocalCall(
        localCall: suspend () -> DOMAIN
    ): DOMAIN {
        return try {
            localCall()
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw LocalException("Database error: ${exception.message}", exception)
        }
    }

    protected fun <ENTITY, DOMAIN> safeLocalFlow(
        callFlow: Flow<ENTITY>,
        mapper: (ENTITY) -> DOMAIN
    ): Flow<DOMAIN> {
        return callFlow
            .map { mapper(it) }
            .catch { exception ->
                throw LocalException("Database stream error: ${exception.message}", exception)
            }
    }
}