package com.base.app.data_local.data_source

import com.base.app.data_local.core.BaseLocalHandler
import com.base.app.data_local.dao.FilmDao
import com.base.app.data_local.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

class FilmLocalDataSource(
    private val filmDao: FilmDao
) : BaseLocalHandler() {

    suspend fun saveFavorite(
        film: FilmEntity
    ) = safeLocalCall { filmDao.insertFilm(film) }

    suspend fun removeFavorite(
        film: FilmEntity
    ) = safeLocalCall { filmDao.deleteFilm(film) }

    fun getFavorites(
    ): Flow<List<FilmEntity>> = safeLocalFlow(filmDao.getAllFavoriteFilms())

    fun searchFavorites(
        name: String
    ): Flow<List<FilmEntity>> = safeLocalFlow(filmDao.searchFilmsByName(name))

    fun getFavoritesSortedAsc(
    ): Flow<List<FilmEntity>> = safeLocalFlow(filmDao.getFilmsOrderedByNameAsc())

    fun getFavoritesSortedDesc(
    ): Flow<List<FilmEntity>> = safeLocalFlow(filmDao.getFilmsOrderedByNameDesc())

    suspend fun isFavorite(
        imdbID: String
    ): Boolean = safeLocalCall { filmDao.isFilmFavorite(imdbID) }
}