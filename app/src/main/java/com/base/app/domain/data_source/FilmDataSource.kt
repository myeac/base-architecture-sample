package com.base.app.domain.data_source

import com.base.app.data_local.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

interface FilmDataSource {
    suspend fun saveFavorite(film: FilmEntity)
    suspend fun removeFavorite(film: FilmEntity)
    fun getFavorites(): Flow<List<FilmEntity>>
    fun searchFavorites(name: String): Flow<List<FilmEntity>>
    fun getFavoritesSortedAsc(): Flow<List<FilmEntity>>
    fun getFavoritesSortedDesc(): Flow<List<FilmEntity>>
    suspend fun isFavorite(imdbID: String): Boolean
}