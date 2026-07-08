package com.base.app.data_local.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

abstract class BaseLocalHandler {

    protected suspend fun <T> safeLocalCall(
        call: suspend () -> T
    ): T {
        return try {
            call()
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw LocalException("Database error: ${exception.message}", exception)
        }
    }

    protected fun <T> safeLocalFlow(
        flow: Flow<T>
    ): Flow<T> {
        return flow.catch { exception ->
            throw LocalException("Database stream error: ${exception.message}", exception)
        }
    }
}