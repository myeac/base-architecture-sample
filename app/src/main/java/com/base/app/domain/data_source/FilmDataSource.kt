package com.base.app.domain.data_source

import com.base.app.domain.model.FilmModel
import kotlinx.coroutines.flow.Flow

interface FilmDataSource {
    suspend fun saveFavorite(film: FilmModel)
    suspend fun removeFavorite(imdbId: String)
    fun getFavorites(): Flow<List<FilmModel>>
    fun searchFavorites(name: String): Flow<List<FilmModel>>
    fun getFavoritesSortedAsc(): Flow<List<FilmModel>>
    fun getFavoritesSortedDesc(): Flow<List<FilmModel>>
    suspend fun isFavorite(imdbID: String): Boolean
}