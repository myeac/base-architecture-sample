package com.base.app.data_remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieRatingDTO(
    @SerialName("Source")
    val source: String? = null,

    @SerialName("Value")
    val value: String? = null
)