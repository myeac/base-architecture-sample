package com.base.app.data_local.data_source

import com.base.app.data_local.core.BaseLocalHandler
import com.base.app.data_local.dao.FilmDao
import com.base.app.data_local.entity.toDomain
import com.base.app.domain.data_source.FilmDataSource
import com.base.app.domain.model.FilmModel
import com.base.app.domain.model.toEntity
import kotlinx.coroutines.flow.Flow

class FilmDataSourceImpl(
    private val filmDao: FilmDao
) : FilmDataSource, BaseLocalHandler() {

    override suspend fun saveFavorite(
        film: FilmModel
    ) = safeLocalCall(
        localCall = { filmDao.insertFilm(film.toEntity()) },
        mapper = { it }
    )

    override suspend fun removeFavorite(
        film: FilmModel
    ) = safeLocalCall(
        localCall = { filmDao.deleteFilm(film.toEntity()) },
        mapper = { it }
    )

    override fun getFavorites(
    ): Flow<List<FilmModel>> = safeLocalFlow(
        callFlow = filmDao.getAllFavoriteFilms(),
        mapper = { it.toDomain() }
    )

    override fun searchFavorites(
        name: String
    ): Flow<List<FilmModel>> = safeLocalFlow(
        callFlow = filmDao.searchFilmsByName(name),
        mapper = { it.toDomain() }
    )

    override fun getFavoritesSortedAsc(
    ): Flow<List<FilmModel>> = safeLocalFlow(
        callFlow = filmDao.getFilmsOrderedByNameAsc(),
        mapper = { it.toDomain() }
    )

    override fun getFavoritesSortedDesc(
    ): Flow<List<FilmModel>> = safeLocalFlow(
        callFlow = filmDao.getFilmsOrderedByNameDesc(),
        mapper = { it.toDomain() }
    )

    override suspend fun isFavorite(
        imdbID: String
    ): Boolean = safeLocalCall(localCall = { filmDao.isFilmFavorite(imdbID) })
}