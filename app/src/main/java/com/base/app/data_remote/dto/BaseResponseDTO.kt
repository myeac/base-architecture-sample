package com.base.app.data_remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseDTO(
    @SerialName("Title")
    val title: String? = null,

    @SerialName("Year")
    val year: String? = null,

    @SerialName("Rated")
    val rated: String? = null,

    @SerialName("Released")
    val release: String? = null,

    @SerialName("Runtime")
    val runtime: String? = null,

    @SerialName("Genre")
    val genre: String? = null,

    @SerialName("Director")
    val director: String? = null,

    @SerialName("Writer")
    val writer: String? = null,

    @SerialName("Actors")
    val actors: String? = null,

    @SerialName("Plot")
    val plot: String? = null,

    @SerialName("Language")
    val language: String? = null,

    @SerialName("Country")
    val country: String? = null,

    @SerialName("Awards")
    val awards: String? = null,

    @SerialName("Poster")
    val posterUrl: String? = null,

    @SerialName("Ratings")
    val ratings: List<MovieRatingDTO>? = null,

    @SerialName("Metascore")
    val metaScore: String? = null,

    @SerialName("imdbRating")
    val imdbRating: String? = null,

    @SerialName("imdbVotes")
    val imdbVotes: String? = null,

    @SerialName("imdbID")
    val imdbID: String? = null,

    @SerialName("Type")
    val type: String? = null,

    @SerialName("DVD")
    val dvd: String? = null,

    @SerialName("BoxOffice")
    val boxOffice: String? = null,

    @SerialName("Production")
    val production: String? = null,

    @SerialName("Website")
    val website: String? = null,

    @SerialName("Response")
    val response: String? = null,

    @SerialName("Error")
    val errorMessages: String? = null,
)