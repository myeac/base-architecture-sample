package com.base.app.domain.use_case

import com.base.app.domain.core.BaseUseCase
import com.base.app.domain.data_source.FilmDataSource

class RemoveFavoriteFilmUseCase(
    private val dataSource: FilmDataSource
) : BaseUseCase<String, Unit>() {

    override suspend fun execute(
        params: String
    ) {
        dataSource.removeFavorite(params)
    }
}