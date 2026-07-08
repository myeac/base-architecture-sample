package com.base.app.data_local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.base.app.data_local.model.FilmRatingLocal

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