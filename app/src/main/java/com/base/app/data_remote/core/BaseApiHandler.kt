package com.base.app.data_remote.core

abstract class BaseApiHandler {

    protected suspend fun <DTO, DOMAIN> safeApiRequest(
        apiCall: suspend () -> BaseResponse<DTO>,
        mapper: (DTO) -> DOMAIN
    ): DOMAIN {
        return when (val response = apiCall()) {
            is BaseResponse.Success -> {
                mapper(response.data)
            }
            is BaseResponse.SuccessNotFound -> {
                throw SuccessNotFoundException(response.message)
            }
            is BaseResponse.Error -> {
                throw ApiException(response.message, response.code)
            }
        }
    }
}