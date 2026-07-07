package com.base.app.data_local.db

import androidx.room.TypeConverter
import com.base.app.data_local.model.FilmRatingLocal
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

class FilmRatingConverter {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromList(
        value: List<FilmRatingLocal>?
    ): String? {
        return value?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun toList(
        value: String?
    ): List<FilmRatingLocal>? {
        return value?.let { json.decodeFromString(it) }
    }
}