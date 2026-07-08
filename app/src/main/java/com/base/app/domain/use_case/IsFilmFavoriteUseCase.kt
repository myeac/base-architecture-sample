package com.base.app.domain.use_case

import com.base.app.domain.core.BaseUseCase
import com.base.app.domain.data_source.FilmDataSource

class IsFilmFavoriteUseCase(
    private val dataSource: FilmDataSource
) : BaseUseCase<String, Boolean>() {

    override suspend fun execute(
        params: String
    ): Boolean {
        return dataSource.isFavorite(params)
    }
}