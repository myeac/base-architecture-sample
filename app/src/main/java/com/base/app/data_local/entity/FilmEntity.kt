package com.base.app.data_local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.base.app.data_local.model.FilmRatingLocal
import com.base.app.data_local.model.toDomain
import com.base.app.domain.model.FilmModel
import kotlin.String

@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey
    val imdbID: String,
    val title: String?,
    val year: String?,
    val rated: String?,
    val released: String?,
    val runtime: String?,
    val genre: String?,
    val director: String?,
    val writer: String?,
    val actors: String?,
    val plot: String?,
    val language: String?,
    val country: String?,
    val awards: String?,
    val poster: String?,
    val ratings: List<FilmRatingLocal>?,
    val metascore: String?,
    val imdbRating: String?,
    val imdbVotes: String?,
    val type: String?,
    val dvd: String?,
    val boxOffice: String?,
    val production: String?,
    val website: String?
)

fun FilmEntity.toDomain() = FilmModel(
    imdbID = this.imdbID ?: "",
    title = this.title ?: "",
    year = this.year ?: "",
    rated = this.rated ?: "",
    release = this.released ?: "",
    runtime = this.runtime ?: "",
    genre = this.genre ?: "",
    director = this.director ?: "",
    writer = this.writer ?: "",
    actors = this.actors ?: "",
    plot = this.plot ?: "",
    language = this.language ?: "",
    country = this.country ?: "",
    awards = this.awards ?: "",
    posterUrl = this.poster ?: "",
    ratings = this.ratings?.toDomain() ?: emptyList(),
    metaScore = this.metascore ?: "",
    imdbRating = this.imdbRating ?: "",
    imdbVotes = this.imdbVotes ?: "",
    type = this.type ?: "",
    dvd = this.dvd ?: "",
    boxOffice = this.boxOffice ?: "",
    production = this.production ?: "",
    website = this.website ?: "",
    response = "",
    errorMessages = "",
)