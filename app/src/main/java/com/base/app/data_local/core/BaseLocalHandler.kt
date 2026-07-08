package com.base.app.data_local.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

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

    protected fun <DOMAIN> safeLocalFlow(
        flow: Flow<DOMAIN>
    ): Flow<DOMAIN> {
        return flow.catch { exception ->
            throw LocalException("Database stream error: ${exception.message}", exception)
        }
    }
}