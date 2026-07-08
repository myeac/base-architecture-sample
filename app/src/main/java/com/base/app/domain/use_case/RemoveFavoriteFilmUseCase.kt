package com.base.app.domain.use_case

import com.base.app.domain.core.BaseUseCase
import com.base.app.domain.data_source.FilmDataSource
import com.base.app.domain.model.FilmModel

class RemoveFavoriteFilmUseCase(
    private val dataSource: FilmDataSource
) : BaseUseCase<FilmModel, Unit>() {

    override suspend fun execute(
        params: FilmModel
    ) {
        dataSource.removeFavorite(params.imdbID)
    }
}