package com.base.app.data_remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseErrorDTO(
    @SerialName("Response")
    val response: String? = null,

    @SerialName("Error")
    val errorMessages: String? = null,
)