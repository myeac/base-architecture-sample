package com.base.app.data_local.data_source

import com.base.app.data_local.core.BaseLocalHandler
import com.base.app.data_local.dao.FilmDao
import com.base.app.data_local.entity.FilmEntity
import com.base.app.domain.data_source.FilmDataSource
import kotlinx.coroutines.flow.Flow

class FilmDataSourceImpl(
    private val filmDao: FilmDao
) : FilmDataSource, BaseLocalHandler() {

    override suspend fun saveFavorite(
        film: FilmEntity
    ) = safeLocalCall { filmDao.insertFilm(film) }

    override suspend fun removeFavorite(
        film: FilmEntity
    ) = safeLocalCall { filmDao.deleteFilm(film) }

    override fun getFavorites(
    ): Flow<List<FilmEntity>> = safeLocalFlow(filmDao.getAllFavoriteFilms())

    override fun searchFavorites(
        name: String
    ): Flow<List<FilmEntity>> = safeLocalFlow(filmDao.searchFilmsByName(name))

    override fun getFavoritesSortedAsc(
    ): Flow<List<FilmEntity>> = safeLocalFlow(filmDao.getFilmsOrderedByNameAsc())

    override fun getFavoritesSortedDesc(
    ): Flow<List<FilmEntity>> = safeLocalFlow(filmDao.getFilmsOrderedByNameDesc())

    override suspend fun isFavorite(
        imdbID: String
    ): Boolean = safeLocalCall { filmDao.isFilmFavorite(imdbID) }
}